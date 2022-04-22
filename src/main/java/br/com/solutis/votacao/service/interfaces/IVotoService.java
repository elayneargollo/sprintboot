package br.com.solutis.votacao.service.interfaces;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.solutis.votacao.model.entity.Voto;

public interface IVotoService {
	Optional<Voto> getById(Integer id);

	Page<Voto> getAll(Pageable paginacao);

	List<Voto> getAll();

	Voto add(Voto voto);
}
