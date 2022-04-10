package br.com.solutis.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.solutis.votacao.model.Pauta;

public interface IPautaRepository extends JpaRepository<Pauta, Integer> {

}
