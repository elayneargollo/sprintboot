package br.com.solutis.votacao.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Value;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "VOTACAO")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Votacao {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@OneToOne
	private Voto voto;
	
	@Value("${LocalDateTime.now()}")
	private LocalDateTime dataVotacao;
	
	public Votacao() { }

	public Votacao(Integer id, Voto voto, LocalDateTime dataVotacao) {
		this.id = id;
		this.voto = voto;
		this.dataVotacao = dataVotacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Voto getVoto() {
		return voto;
	}

	public void setVoto(Voto voto) {
		this.voto = voto;
	}

	public LocalDateTime getDataVotacao() {
		return dataVotacao;
	}

	public void setDataVotacao(LocalDateTime dataVotacao) {
		this.dataVotacao = dataVotacao;
	}
}
