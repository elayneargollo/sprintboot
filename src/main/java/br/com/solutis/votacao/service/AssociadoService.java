package br.com.solutis.votacao.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.solutis.votacao.model.Associado;
import br.com.solutis.votacao.repository.IAssociadoRepository;

@Service
public class AssociadoService implements IAssociadoService{

	@Autowired
	IAssociadoRepository associadoRepository;
	
	public Optional<Associado> GetById(Integer id){
        return  associadoRepository.findById(id);
    }
	
	public List<Associado> GetAll(){
        return associadoRepository.findAll();
    }
}
