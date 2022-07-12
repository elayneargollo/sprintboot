package br.com.solutis.votacao.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.solutis.votacao.exception.NotFoundException;
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

		return pautaSalva;
	}

	@Override
	public Optional<Pauta> getById(Integer id) {
		logger.log(Level.INFO, "Método GetById com id:: {0} ", id);
		
		Optional<Pauta> pauta = pautaRepository.findById(id);
		
		if(pauta.isEmpty())
			throw new NotFoundException("Pauta não encontrada");
		
		return pauta;
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

		var pauta = getByIdAndStatus(id, Status.ABERTO.toString(), Status.FECHADO.toString());
		pautaRepository.alterarStatusPauta(Status.ABERTO, id);
		
		return ("Pauta " + pauta.get().getSessao().getDescricao() + "aberta para votação !");
	}
	
	@SuppressWarnings("unlikely-arg-type")
	private Optional<Pauta> getByIdAndStatus(Integer id, String status, String olderStatus) {
		logger.log(Level.INFO, "Método GetByIdAndStatus com id:: {0}", id);
		
		Optional<Pauta> pauta = pautaRepository.findById(id);
		
		if(pauta.isEmpty() || status.equals(pauta.get().getStatus()) || olderStatus.equals(pauta.get().getStatus()))
			throw new NotFoundException("Pauta não encontrada ou não atende ao status necessário para esta ação.");
		
		return pauta;
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
		
		Optional<Pauta> pauta = pautaRepository.findById(id);
		
		if(pauta.isEmpty()) throw new NotFoundException("Pauta não encontrada.");
			
		return obterResultadoVotacao(pauta);		
	}
	
	private ResultadoVotacao obterResultadoVotacao(Optional<Pauta> pauta)
	{
		logger.log(Level.INFO, "Método obterResultadoVotacao");	
		
		List<Voto> votosTotaisPauta = votoRepository.obterVotosPorPauta(pauta.get().getId());

		var quantidadeVotoPositivo = votosTotaisPauta.stream().filter(v -> v.getDescricao() == OpcaoVoto.SIM).collect(Collectors.toList()).size();
		var quantidadeVotoNegativo = votosTotaisPauta.stream().filter(v -> v.getDescricao() == OpcaoVoto.NAO).collect(Collectors.toList()).size();
		
		var vencedor = quantidadeVotoPositivo > quantidadeVotoNegativo ? OpcaoVoto.SIM : OpcaoVoto.NAO;
		var tipoVotacao = pauta.get().getStatus().equals(Status.FECHADO) ? "Completa" : "Parcial";

		return montarResultado(votosTotaisPauta.size(), vencedor, tipoVotacao, quantidadeVotoNegativo, quantidadeVotoPositivo );
	}
	
	private ResultadoVotacao montarResultado(int votosTotaisPauta, OpcaoVoto vencedor, String tipoVotacao, int quantidadeVotoNegativo, int quantidadeVotoPositivo)
	{
		logger.log(Level.INFO, "Método montarResultado");	
		
		ResultadoVotacao resultado = new ResultadoVotacao();
		
		var votos = votosTotaisPauta == 0 ? 1 : votosTotaisPauta;
		
		resultado.setPercentualVotoNegativo((quantidadeVotoNegativo * 100) / votos);
		resultado.setPercentualVotoPositivo((quantidadeVotoPositivo * 100) / votos);
		
		resultado.setQuantidadeVotoNegativo(quantidadeVotoNegativo);
		resultado.setQuantidadeVotoPositivo(quantidadeVotoPositivo);
		
		resultado.setVencedor(vencedor);
		resultado.setTipoResultado(tipoVotacao);
		
		resultado.setDataApuracao(new Date());
		
		return resultado;
	}
}
