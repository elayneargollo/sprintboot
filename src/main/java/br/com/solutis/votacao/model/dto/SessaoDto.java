package br.com.solutis.votacao.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessaoDto {
	@NotEmpty
	@NotBlank
	@NotNull
	@Size(min = 10, max = 255)
	@JsonProperty("descricao")
	private String descricao;

	@NotEmpty
	@NotBlank
	@NotNull
	@Size(min = 10, max = 255)
	@JsonProperty("tipo")
	private String tipo;
	
	@JsonProperty("tempoDuracao")
	private long tempoDuracao;

	public SessaoDto() {
	}

	public SessaoDto(String descricao, long tempoDuracao, String tipo) {
		this.descricao = descricao;
		this.tipo = tipo;
		setTempoDuracao(tempoDuracao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public long getTempoDuracao() {
		return tempoDuracao;
	}

	public void setTempoDuracao(long tempoDuracao) {
		if (tempoDuracao < 0)
			tempoDuracao = 60;

		this.tempoDuracao = tempoDuracao;
	}
}
