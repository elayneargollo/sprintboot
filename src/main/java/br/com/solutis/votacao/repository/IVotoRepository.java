package br.com.solutis.votacao.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.solutis.votacao.model.Voto;

public interface IVotoRepository extends JpaRepository<Voto, Integer> {
	
	@Modifying
    @Transactional
    @Query(value = "SELECT * FROM VOTO v WHERE v.pauta_Id = :idPauta", nativeQuery = true)
	List<Voto> ObterVotosPorPauta(@Param("idPauta")Integer idPauta);
}
