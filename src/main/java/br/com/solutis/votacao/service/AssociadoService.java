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
import br.com.solutis.votacao.exception.ServiceCpfException;
import br.com.solutis.votacao.model.entity.Associado;
import br.com.solutis.votacao.repository.IAssociadoRepository;
import br.com.solutis.votacao.repository.ServiceCpf;
import br.com.solutis.votacao.service.interfaces.IAssociadoService;

@Service
public class AssociadoService implements IAssociadoService {

	@Autowired
	IAssociadoRepository associadoRepository;
	@Autowired
	ServiceCpf serviceCpf;
	
	Logger logger = Logger.getLogger(AssociadoService.class.getName());

	public Optional<Associado> getById(Integer id) {
		logger.log(Level.INFO, "Método GetById com id:: {0} ", id);
		
		if(!associadoRepository.existsById(id))
			throw new NotFoundException("Associado não encontrado");
			
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
		logger.log(Level.INFO, "Método add com cpf:: {0} ", associado.getCpf());
		
		var response = serviceCpf.validarCpf(associado.getCpf());
		
		if(!response.getIsValid())
			throw new ServiceCpfException("CPF informado não é válido");
			
		return associadoRepository.save(associado);
	}
}
