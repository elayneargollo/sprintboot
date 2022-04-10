package br.com.solutis.votacao.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.solutis.votacao.model.Pauta;
import br.com.solutis.votacao.repository.IPautaRepository;
import br.com.solutis.votacao.repository.ISessaoRepository;
import br.com.solutis.votacao.service.interfaces.IPautaService;

@Service
public class PautaService implements IPautaService{

	@Autowired
	IPautaRepository pautaRepository;
	@Autowired
	ISessaoRepository sessaoRepository;

	@Override
	public Pauta Add(Pauta pauta){
		var sessao = sessaoRepository.save(pauta.getSessao());
		pauta.getSessao().setId(sessao.getId());
		
		return pautaRepository.save(pauta);
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
}
