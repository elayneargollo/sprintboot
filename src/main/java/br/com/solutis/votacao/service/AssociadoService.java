package br.com.solutis.votacao.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.solutis.votacao.model.entity.Associado;
import br.com.solutis.votacao.repository.IAssociadoRepository;
import br.com.solutis.votacao.service.interfaces.IAssociadoService;

@Service
public class AssociadoService implements IAssociadoService {

	@Autowired
	IAssociadoRepository associadoRepository;
	Logger logger = Logger.getLogger(AssociadoService.class.getName());

	public Optional<Associado> getById(Integer id) {
		logger.log(Level.INFO, "Método GetById com id:: {0} ", id);

		return associadoRepository.findById(id);
	}

	@Override
	public Page<Associado> getAll(Pageable paginacao) {
		logger.info("Método GetAll com paginação");
		return associadoRepository.findAll(paginacao);
	}

	@Override
	public List<Associado> getAll() {
		logger.info("Método GetAll");
		return associadoRepository.findAll();
	}

	@Override
	public Associado add(Associado associado) {
		logger.info("Método Add");
		return associadoRepository.save(associado);
	}
}
