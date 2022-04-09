package br.com.solutis.votacao.service.interfaces;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.solutis.votacao.model.Voto;

public interface IVotoService {
	Optional<Voto> GetById(Integer id);
	Page<Voto> GetAll(Pageable paginacao);
	List<Voto> GetAll();
	Voto Add(Voto voto) throws Exception;
}
