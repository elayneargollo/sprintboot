package br.com.solutis.votacao.service.interfaces;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.solutis.votacao.model.entity.Sessao;

public interface ISessaoService {
	Optional<Sessao> getById(Integer id);

	Page<Sessao> getAll(Pageable paginacao);

	List<Sessao> getAll();

	Sessao add(Sessao sessao) throws Exception;
}
