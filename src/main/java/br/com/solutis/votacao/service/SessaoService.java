package br.com.solutis.votacao.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

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
	Logger logger = Logger.getLogger(SessaoService.class.getName());

	@Override
	public Sessao Add(Sessao sessao) throws Exception {
		logger.info("Método Add");
		return sesSaoRepository.save(sessao);
	}

	@Override
	public Optional<Sessao> GetById(Integer id) {
		logger.info("Método GetById com id: " +id);	
		return sesSaoRepository.findById(id);
	}

	@Override
	public Page<Sessao> GetAll(Pageable paginacao) {
		logger.info("Método GetAll com paginação");	
		return sesSaoRepository.findAll(paginacao);
	}

	@Override
	public List<Sessao> GetAll() {
		logger.info("Método GetAll");	
		return sesSaoRepository.findAll();
	}
}
