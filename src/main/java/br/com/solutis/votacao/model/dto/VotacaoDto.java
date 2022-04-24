package br.com.solutis.votacao.model.dto;

import java.time.LocalDateTime;
import javax.persistence.OneToOne;

public class VotacaoDto {
	@OneToOne
	private VotoDto voto;
	private LocalDateTime dataVotacao;

	public VotacaoDto() {
	}

	public VotacaoDto(VotoDto voto) {
		this.voto = voto;
		this.dataVotacao = LocalDateTime.now();
	}

	public VotoDto getVoto() {
		return voto;
	}

	public void setVoto(VotoDto voto) {
		this.voto = voto;
	}

	public LocalDateTime getDataVotacao() {
		return dataVotacao;
	}

	public void setDataVotacao(LocalDateTime dataVotacao) {
		this.dataVotacao = dataVotacao;
	}
}
