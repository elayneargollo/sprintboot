package br.com.solutis.votacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.solutis.votacao.model.entity.Sessao;
import br.com.solutis.votacao.repository.IPautaRepository;
import br.com.solutis.votacao.repository.ISessaoRepository;
import br.com.solutis.votacao.service.interfaces.ISessaoService;

@Service
public class SessaoService implements ISessaoService {

	@Autowired
	ISessaoRepository sesSaoRepository;
	@Autowired
	IPautaRepository pautaRepository;

	@Override
	public Sessao Add(Sessao sessao) throws Exception {
		return sesSaoRepository.save(sessao);
	}

	@Override
	public Optional<Sessao> GetById(Integer id) {
		return sesSaoRepository.findById(id);
	}

	@Override
	public Page<Sessao> GetAll(Pageable paginacao) {
		return sesSaoRepository.findAll(paginacao);
	}

	@Override
	public List<Sessao> GetAll() {
		return sesSaoRepository.findAll();
	}
}
