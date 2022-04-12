package br.com.solutis.votacao.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.solutis.votacao.exception.PautaNaoAbertaException;
import br.com.solutis.votacao.model.Pauta;
import br.com.solutis.votacao.model.Sessao;
import br.com.solutis.votacao.model.enumeracao.Status;
import br.com.solutis.votacao.repository.IPautaRepository;
import br.com.solutis.votacao.repository.ISessaoRepository;
import br.com.solutis.votacao.service.interfaces.IPautaService;

@Service
public class PautaService implements IPautaService {

	@Autowired
	IPautaRepository pautaRepository;
	@Autowired
	ISessaoRepository sessaoRepository;

	@Override
	public Pauta Add(Pauta pauta) {
		Sessao sessao = sessaoRepository.save(pauta.getSessao());
		pauta.getSessao().setId(sessao.getId());
		
		Pauta pautaSalva = pautaRepository.save(pauta);
		
		if(pauta.getStatus() == Status.ABERTO)
			fecharVotacao(pautaSalva.getId(), sessao.getTempoDuracao());

		return pautaSalva;
	}

	@Override
	public Optional<Pauta> GetById(Integer id) {
		return pautaRepository.findById(id);
	}

	@Override
	public Page<Pauta> GetAll(Pageable paginacao) {
		return pautaRepository.findAll(paginacao);
	}

	@Override
	public List<Pauta> GetAll() {
		return pautaRepository.findAll();
	}

	@Override
	public String IniciarPauta(Integer id) {

		Pauta pauta = pautaRepository.getById(id);

		if (pauta.getStatus() == Status.ABERTO || pauta.getStatus() == Status.FECHADO)
			throw new PautaNaoAbertaException("Pauta encontra-se aberta ou fechada !");
		
		pautaRepository.AlterarStatusPauta(Status.ABERTO, id);
		fecharVotacao(id, pauta.getSessao().getTempoDuracao());
		
		return "Pauta aberta !";
	}
	
	public void fecharVotacao(Integer id, long duracao)
	{
		 new Thread(() -> {
             try{
            	 Thread.sleep(duracao);
                 pautaRepository.AlterarStatusPauta(Status.FECHADO, id);
             } catch (InterruptedException e) {
                 Thread.currentThread().interrupt();
             }
         }).start();
	}
}
