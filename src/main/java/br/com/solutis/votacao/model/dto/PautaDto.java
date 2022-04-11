package br.com.solutis.votacao.model.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import br.com.solutis.votacao.model.enumeracao.Status;

public class PautaDto {
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToOne
	private SessaoDto sessaoDto;
	
	public PautaDto() {}

	public PautaDto(Status status, SessaoDto sessaoDto) {
		this.status = status;
		this.sessaoDto = sessaoDto;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public SessaoDto getSessao() {
		return sessaoDto;
	}

	public void setSessao(SessaoDto sessao) {
		this.sessaoDto = sessao;
	}
}
