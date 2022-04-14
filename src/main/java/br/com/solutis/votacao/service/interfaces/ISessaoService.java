package br.com.solutis.votacao.service.interfaces;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.solutis.votacao.model.entity.Sessao;

public interface ISessaoService {
	Optional<Sessao> GetById(Integer id);

	Page<Sessao> GetAll(Pageable paginacao);

	List<Sessao> GetAll();

	Sessao Add(Sessao sessao) throws Exception;
}
