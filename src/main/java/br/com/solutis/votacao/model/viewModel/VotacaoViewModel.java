package br.com.solutis.votacao.model.viewModel;

import java.time.LocalDateTime;
import javax.persistence.OneToOne;

public class VotacaoViewModel {
	
	private Integer id;

	@OneToOne
	private VotoViewModel voto;

	private LocalDateTime dataVotacao;

	public VotacaoViewModel() {
	}

	public VotacaoViewModel(Integer id, VotoViewModel voto, LocalDateTime dataVotacao) {
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

	public VotoViewModel getVoto() {
		return voto;
	}

	public void setVoto(VotoViewModel voto) {
		this.voto = voto;
	}

	public LocalDateTime getDataVotacao() {
		return dataVotacao;
	}

	public void setDataVotacao(LocalDateTime dataVotacao) {
		this.dataVotacao = dataVotacao;
	}
}
