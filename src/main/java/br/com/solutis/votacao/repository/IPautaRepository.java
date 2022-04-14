package br.com.solutis.votacao.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.solutis.votacao.model.entity.Pauta;
import br.com.solutis.votacao.model.enumeracao.Status;

public interface IPautaRepository extends JpaRepository<Pauta, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE Pauta p SET p.status = :status WHERE p.id = :id")
	void AlterarStatusPauta(@Param("status") Status status, @Param("id") Integer id);
}
