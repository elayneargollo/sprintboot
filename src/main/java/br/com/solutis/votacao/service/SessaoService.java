package br.com.solutis.votacao.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.solutis.votacao.exception.NotFoundException;
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
	public Sessao add(Sessao sessao) throws Exception {
		logger.info("Método Add");
		return sesSaoRepository.save(sessao);
	}

	@Override
	public Optional<Sessao> getById(Integer id) {
		logger.log(Level.INFO, "Método GetById com id:: {0} ", id);
		
		var sessao = sesSaoRepository.findById(id);
		
		if(sessao.isEmpty())
			throw new NotFoundException("Sessão não encontrada");
		
		return sesSaoRepository.findById(id);
	}

	@Override
	public Page<Sessao> getAll(Pageable paginacao) {
		logger.info("Método GetAll com paginação");	
		return sesSaoRepository.findAll(paginacao);
	}

	@Override
	public List<Sessao> getAll() {
		logger.info("Método GetAll");	
		return sesSaoRepository.findAll();
	}
}
