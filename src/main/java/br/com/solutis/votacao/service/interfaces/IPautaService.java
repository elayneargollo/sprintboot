package br.com.solutis.votacao.service.interfaces;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.solutis.votacao.model.Pauta;

public interface IPautaService {
	Optional<Pauta> GetById(Integer id);
	Page<Pauta> GetAll(Pageable paginacao);
	List<Pauta> GetAll();
	Pauta Add(Pauta pauta);
}
