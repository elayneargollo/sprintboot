package br.com.solutis.votacao.model.viewModel;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import br.com.solutis.votacao.model.dto.AssociadoDto;
import br.com.solutis.votacao.model.entity.Associado;
import br.com.solutis.votacao.model.enumeracao.OpcaoVoto;

public class VotoViewModel {
	private Integer id;
	@Enumerated(EnumType.STRING)
	private OpcaoVoto descricao;
	private LocalDateTime dataVotacao;
	@Column(name = "associadoId", nullable = false)
	private Integer associadoId;
	@Column(name = "pautaId", nullable = false)
	private Integer pautaId;

	public VotoViewModel() {
	}

	public VotoViewModel(Integer id, OpcaoVoto descricao, Integer associadoId, Integer pautaId) {
		this.id = id;
		this.descricao = descricao;
		this.associadoId = associadoId;
		this.pautaId = pautaId;
		this.dataVotacao = LocalDateTime.now();
	}

	public VotoViewModel(OpcaoVoto descricao, Integer associadoId, Integer pautaId) {
		this.descricao = descricao;
		this.associadoId = associadoId;
		this.pautaId = pautaId;
		this.dataVotacao = LocalDateTime.now();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OpcaoVoto getDescricao() {
		return descricao;
	}

	public void setDescricao(OpcaoVoto descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataVotacao() {
		return dataVotacao;
	}

	public void setDataVotacao(LocalDateTime dataVotacao) {
		this.dataVotacao = dataVotacao;
	}

	public Integer getAssociadoId() {
		return associadoId;
	}

	public void setAssociadoId(Integer associadoId) {
		this.associadoId = associadoId;
	}

	public Integer getPautaId() {
		return pautaId;
	}

	public void setPautaId(Integer pautaId) {
		this.pautaId = pautaId;
	}

	public Associado converterByAssociado(AssociadoDto associadoDto) {
		return new Associado(associadoDto.getNome(), associadoDto.getEmail());
	}
}
