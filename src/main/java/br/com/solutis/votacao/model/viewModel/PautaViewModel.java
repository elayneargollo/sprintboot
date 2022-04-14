package br.com.solutis.votacao.model.viewModel;

import java.time.LocalDateTime;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import br.com.solutis.votacao.model.enumeracao.Status;

public class PautaViewModel {
	private Integer id;
	@Enumerated(EnumType.STRING)
	private Status status;
	@OneToOne
	private SessaoViewModel sessao;
	public LocalDateTime dataAbertura;

	public PautaViewModel() {
	}

	public PautaViewModel(Integer id, Status status, SessaoViewModel sessao) {
		super();
		this.id = id;
		this.status = status;
		this.sessao = sessao;
		setDataAbertura(LocalDateTime.now());
	}

	public PautaViewModel(Status status, SessaoViewModel sessao) {
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

	public SessaoViewModel getSessao() {
		return sessao;
	}

	public void setSessao(SessaoViewModel sessao) {
		this.sessao = sessao;
	}

	@Override
	public String toString() {
		return "Pauta [id=" + id + ", status=" + status + ", sessao=" + sessao + "]";
	}
}
