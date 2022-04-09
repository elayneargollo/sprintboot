package br.com.solutis.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.solutis.votacao.model.Voto;

public interface IVotoRepository extends JpaRepository<Voto, Integer> {

}
