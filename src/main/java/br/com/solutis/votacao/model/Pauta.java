package br.com.solutis.votacao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.solutis.votacao.model.enumeracao.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "PAUTA")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Pauta {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToOne
	private Sessao sessao;
	
	public Pauta() {}

	public Pauta(Integer id, Status status, Sessao sessao) {
		super();
		this.id = id;
		this.status = status;
		this.sessao = sessao;
	}
	
	public Pauta(Status status, Sessao sessao) {
		this.status = status;
		this.sessao = sessao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	@Override
	public String toString() {
		return "Pauta [id=" + id + ", status=" + status + ", sessao=" + sessao + "]";
	}
}
