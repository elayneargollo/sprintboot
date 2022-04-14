package br.com.solutis.votacao.service.interfaces;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.solutis.votacao.model.Associado;

public interface IAssociadoService {
	Optional<Associado> GetById(Integer id);

	Page<Associado> GetAll(Pageable paginacao);

	List<Associado> GetAll();

	Associado Add(Associado associado);
}
