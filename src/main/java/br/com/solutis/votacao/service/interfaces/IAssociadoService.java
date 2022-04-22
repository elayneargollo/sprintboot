package br.com.solutis.votacao.service.interfaces;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.solutis.votacao.model.entity.Associado;

public interface IAssociadoService {
	Optional<Associado> getById(Integer id);

	Page<Associado> getAll(Pageable paginacao);

	List<Associado> getAll();

	Associado add(Associado associado);
}
