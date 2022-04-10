package br.com.solutis.votacao.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.solutis.votacao.model.Associado;
import br.com.solutis.votacao.repository.IAssociadoRepository;
import br.com.solutis.votacao.service.interfaces.IAssociadoService;

@Service
public class AssociadoService implements IAssociadoService{

	@Autowired
	IAssociadoRepository associadoRepository;
	
	public Optional<Associado> GetById(Integer id){
        return associadoRepository.findById(id);
	}

	@Override
	public Page<Associado> GetAll(Pageable paginacao) {
		return associadoRepository.findAll(paginacao);
	}

	@Override
	public List<Associado> GetAll() {
		return associadoRepository.findAll();
	}

	@Override
	public Associado Add(Associado associado) {
		return associadoRepository.save(associado);
	}
}
