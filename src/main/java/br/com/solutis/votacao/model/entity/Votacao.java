package br.com.solutis.votacao.model.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VOTACAO")
public class Votacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@OneToOne
	private Voto voto;

	private LocalDateTime dataVotacao;

	public Votacao() {
	}

	public Votacao(Integer id, Voto voto) {
		this.id = id;
		this.voto = voto;
		this.dataVotacao = LocalDateTime.now();
	}
	
	public Votacao(Voto voto) {
		this.voto = voto;
		this.dataVotacao = LocalDateTime.now();
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
