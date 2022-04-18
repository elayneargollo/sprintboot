package br.com.solutis.votacao.service;

import java.util.List;
import java.util.Optional;
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
	public Pauta Add(Pauta pauta) {
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
	public Optional<Pauta> GetById(Integer id) {
		logger.info("Método GetById com id: " +id);		
		return pautaRepository.findById(id);
	}

	@Override
	public Page<Pauta> GetAll(Pageable paginacao) {
		logger.info("Método GetAll com paginação");
		return pautaRepository.findAll(paginacao);
	}

	@Override
	public List<Pauta> GetAll() {
		logger.info("Método GetAll");
		return pautaRepository.findAll();
	}

	@Override
	public String IniciarPauta(Integer id) {
		logger.info("Método IniciarPauta com id: " +id);

		Pauta pauta = pautaRepository.getById(id);

		if (pauta.getStatus() == Status.ABERTO || pauta.getStatus() == Status.FECHADO)
		{
			logger.info("Solicitação de iniciar pauta com status: " +pauta.getStatus());
			throw new PautaNaoAbertaException("Pauta encontra-se aberta ou fechada !");
		}

		pautaRepository.AlterarStatusPauta(Status.ABERTO, id);
		fecharVotacao(id, pauta.getSessao().getTempoDuracao());

		return "Pauta aberta !";
	}

	public void fecharVotacao(Integer id, long duracao) {
		new Thread(() -> {
			try {
				Thread.sleep(duracao);
				pautaRepository.AlterarStatusPauta(Status.FECHADO, id);
			} catch (InterruptedException e) {
				
				logger.info("Erro: " +e.getMessage() + "\n" + "Ao fechar pauta com id: " +id);
				Thread.currentThread().interrupt();
			}
		}).start();
	}

	@Override
	public ResultadoVotacao ObterResultadoPorPauta(Integer id) {

		logger.info("Método ObterResultadoPorPauta om id: " +id);
		
		Pauta pauta = pautaRepository.getById(id);

		if (pauta.getStatus() == Status.ABERTO || pauta.getStatus() == Status.CRIADO) {
			logger.info("Fechar a pauta para contagem dos votos !");
			pautaRepository.AlterarStatusPauta(Status.FECHADO, id);
		}

		return ObterResultadoVotacao(id);
	}

	private ResultadoVotacao ObterResultadoVotacao(Integer idPauta) {
		logger.info("Método ObterResultadoVotacao com id: " +idPauta);
		
		List<Voto> votosTotaisPauta = votoRepository.ObterVotosPorPauta(idPauta);

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
