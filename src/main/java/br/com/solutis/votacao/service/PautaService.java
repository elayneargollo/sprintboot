package br.com.solutis.votacao.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.solutis.votacao.exception.PautaNaoAbertaException;
import br.com.solutis.votacao.model.entity.Pauta;
import br.com.solutis.votacao.model.entity.ResultadoVotacao;
import br.com.solutis.votacao.model.entity.Sessao;
import br.com.solutis.votacao.model.entity.Voto;
import br.com.solutis.votacao.model.enumeracao.OpcaoVoto;
import br.com.solutis.votacao.model.enumeracao.Status;
import br.com.solutis.votacao.repository.IPautaRepository;
import br.com.solutis.votacao.repository.ISessaoRepository;
import br.com.solutis.votacao.repository.IVotoRepository;
import br.com.solutis.votacao.service.interfaces.IPautaService;

@Service
public class PautaService implements IPautaService {

	@Autowired
	IPautaRepository pautaRepository;
	@Autowired
	ISessaoRepository sessaoRepository;
	@Autowired
	IVotoRepository votoRepository;
	Logger logger = Logger.getLogger(PautaService.class.getName());

	@Override
	public Pauta add(Pauta pauta) {
		logger.info("Método Add");
		 
		Sessao sessao = sessaoRepository.save(pauta.getSessao());
		pauta.getSessao().setId(sessao.getId());

		Pauta pautaSalva = pautaRepository.save(pauta);

		if (pauta.getStatus() == Status.ABERTO)
		{
			logger.info("Fechando votação ...");
			fecharVotacao(pautaSalva.getId(), sessao.getTempoDuracao());
		}

		return pautaSalva;
	}

	@Override
	public Optional<Pauta> getById(Integer id) {
		logger.log(Level.INFO, "Método GetById com id:: {0} ", id);
		return pautaRepository.findById(id);
	}

	@Override
	public Page<Pauta> getAll(Pageable paginacao) {
		logger.info("Método GetAll com paginação");
		return pautaRepository.findAll(paginacao);
	}

	@Override
	public List<Pauta> getAll() {
		logger.info("Método GetAll");
		return pautaRepository.findAll();
	}

	@Override
	public String iniciarPauta(Integer id) {
		logger.log(Level.INFO, "Método IniciarPauta com id:: {0} ", id);

		Pauta pauta = pautaRepository.getById(id);

		if (pauta.getStatus() == Status.ABERTO || pauta.getStatus() == Status.FECHADO)
		{
			logger.info("Solicitação de iniciar pauta com status: " +pauta.getStatus());
			throw new PautaNaoAbertaException("Pauta encontra-se aberta ou fechada !");
		}

		pautaRepository.alterarStatusPauta(Status.ABERTO, id);
		fecharVotacao(id, pauta.getSessao().getTempoDuracao());

		return "Pauta aberta !";
	}

	public void fecharVotacao(Integer id, long duracao) {
		new Thread(() -> {
			try {
				Thread.sleep(duracao);
				pautaRepository.alterarStatusPauta(Status.FECHADO, id);
			} catch (InterruptedException e) {
				
				logger.info("Erro: " +e.getMessage() + "\n" + "Ao fechar pauta com id: " +id);
				Thread.currentThread().interrupt();
			}
		}).start();
	}

	@Override
	public ResultadoVotacao obterResultadoPorPauta(Integer id) {

		logger.log(Level.INFO, "Método ObterResultadoPorPauta com id:: {0} ", id);
		
		Pauta pauta = pautaRepository.getById(id);

		if (pauta.getStatus() == Status.ABERTO || pauta.getStatus() == Status.CRIADO) {
			logger.info("Fechar a pauta para contagem dos votos !");
			pautaRepository.alterarStatusPauta(Status.FECHADO, id);
		}

		return obterResultadoVotacao(id);
	}

	private ResultadoVotacao obterResultadoVotacao(Integer idPauta) {
		logger.log(Level.INFO, "Método ObterResultadoVotacao com id:: {0} ", idPauta);

		List<Voto> votosTotaisPauta = votoRepository.obterVotosPorPauta(idPauta);

		var quantidadeVotoPositivo = votosTotaisPauta.stream().filter(v -> v.getDescricao() == OpcaoVoto.SIM).toList().size();
		var quantidadeVotoNegativo = votosTotaisPauta.stream().filter(v -> v.getDescricao() == OpcaoVoto.NAO).toList().size();
		
		var vencedor = quantidadeVotoPositivo > quantidadeVotoNegativo ? OpcaoVoto.SIM : OpcaoVoto.NAO;

		ResultadoVotacao resultado = new ResultadoVotacao();

		resultado.setPercentualVotoNegativo((quantidadeVotoNegativo * 100) / votosTotaisPauta.size());
		resultado.setPercentualVotoPositivo((quantidadeVotoPositivo * 100) / votosTotaisPauta.size());
		resultado.setQuantidadeVotoNegativo(quantidadeVotoNegativo);
		resultado.setQuantidadeVotoPositivo(quantidadeVotoPositivo);
		resultado.setVencedor(vencedor);

		return resultado;
	}
}
