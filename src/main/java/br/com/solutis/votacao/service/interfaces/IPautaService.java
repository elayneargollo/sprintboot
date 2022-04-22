package br.com.solutis.votacao.service.interfaces;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.solutis.votacao.model.entity.Pauta;
import br.com.solutis.votacao.model.entity.ResultadoVotacao;

public interface IPautaService {
	Optional<Pauta> getById(Integer id);

	Page<Pauta> getAll(Pageable paginacao);

	List<Pauta> getAll();

	Pauta add(Pauta pauta);

	String iniciarPauta(Integer id);

	ResultadoVotacao obterResultadoPorPauta(Integer id);
}
