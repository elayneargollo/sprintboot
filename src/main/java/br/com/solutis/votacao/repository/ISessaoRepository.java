package br.com.solutis.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.solutis.votacao.model.Sessao;


@Repository
public interface ISessaoRepository extends JpaRepository<Sessao, Integer> {

}
