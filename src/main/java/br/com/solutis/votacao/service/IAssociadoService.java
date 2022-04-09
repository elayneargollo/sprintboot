package br.com.solutis.votacao.service;

import java.util.List;
import java.util.Optional;

import br.com.solutis.votacao.model.Associado;

public interface IAssociadoService {
	Optional<Associado> GetById(Integer id);
	List<Associado> GetAll();
}
