package br.com.solutis.votacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.solutis.votacao.model.Associado;
import br.com.solutis.votacao.model.Sessao;
import br.com.solutis.votacao.model.Voto;
import br.com.solutis.votacao.repository.IAssociadoRepository;
import br.com.solutis.votacao.repository.IVotoRepository;
import br.com.solutis.votacao.service.interfaces.IVotoService;

@Service
public class VotoService implements IVotoService{

	@Autowired
	IVotoRepository votoRepository;
	@Autowired
	IAssociadoRepository associadoRepository;
	
	@Override
	public Voto Add(Voto voto) throws Exception {
	
		/*Associado associadoEncontrado = associadoRepository.getById(voto.getAssociado().getId());
		
		if(associadoEncontrado == null) {
			 throw new Exception("Associado não encontrado");
		}*/
		
		return votoRepository.save(voto);
	}
	
	@Override
	public Optional<Voto> GetById(Integer id) {
		return votoRepository.findById(id);
	}

	@Override
	public Page<Voto> GetAll(Pageable paginacao) {
		return votoRepository.findAll(paginacao);
	}

	@Override
	public List<Voto> GetAll() {
		return votoRepository.findAll();
	}
}
