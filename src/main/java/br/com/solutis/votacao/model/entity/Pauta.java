package br.com.solutis.votacao.model.entity;

import java.time.LocalDateTime;
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
import lombok.Builder;

@Entity
@Table(name = "PAUTA")
@Builder
public class Pauta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private Status status;
	@OneToOne
	private Sessao sessao;
	public LocalDateTime dataAbertura;

	public Pauta() {
	}

	public Pauta(Integer id, Status status, Sessao sessao, LocalDateTime dataAbertura) {
		super();
		this.id = id;
		this.status = status;
		this.sessao = sessao;
		this.dataAbertura = dataAbertura;
	}

	public Pauta(Integer id, Status status, Sessao sessao) {
		this.id = id;
		this.status = status;
		this.sessao = sessao;
		setDataAbertura(LocalDateTime.now());
	}

	public Pauta(Status status, Sessao sessao) {
		this.status = status;
		this.sessao = sessao;
		setDataAbertura(LocalDateTime.now());
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
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
