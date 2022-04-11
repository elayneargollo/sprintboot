package br.com.solutis.votacao.model.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Min;
import br.com.solutis.votacao.model.enumeracao.OpcaoVoto;

public class VotoDto {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private OpcaoVoto descricao;
	
	@Min(value=1, message="Valor deve ser maior que zero")
	private Integer associadoId;
	
	@Min(value=1, message="Valor deve ser maior que zero")
	private Integer pautaId;
	
	public VotoDto() {}
	
	public VotoDto(OpcaoVoto descricao, Integer associadoId, Integer pautaId) {
		this.descricao = descricao;
		this.associadoId = associadoId;
		this.pautaId = pautaId;
	}

	public OpcaoVoto getDescricao() {
		return descricao;
	}
	
	public void setDescricao(OpcaoVoto descricao) {
		this.descricao = descricao;
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
}
