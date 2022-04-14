package br.com.solutis.votacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.solutis.votacao.model.Sessao;
import br.com.solutis.votacao.repository.IPautaRepository;
import br.com.solutis.votacao.repository.ISessaoRepository;
import br.com.solutis.votacao.service.interfaces.ISessaoService;

@Service
public class SessaoService implements ISessaoService {

	@Autowired
	ISessaoRepository sesaoRepository;
	@Autowired
	IPautaRepository pautaRepository;

	@Override
	public Sessao Add(Sessao sessao) throws Exception {
		return sesaoRepository.save(sessao);
	}

	@Override
	public Optional<Sessao> GetById(Integer id) {
		return sesaoRepository.findById(id);
	}

	@Override
	public Page<Sessao> GetAll(Pageable paginacao) {
		return sesaoRepository.findAll(paginacao);
	}

	@Override
	public List<Sessao> GetAll() {
		return sesaoRepository.findAll();
	}
}
