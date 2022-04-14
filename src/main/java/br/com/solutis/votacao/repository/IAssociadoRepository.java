package br.com.solutis.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.solutis.votacao.model.entity.Associado;

@Repository
public interface IAssociadoRepository extends JpaRepository<Associado, Integer> {
}
