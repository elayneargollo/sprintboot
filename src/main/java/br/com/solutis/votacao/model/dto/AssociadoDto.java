package br.com.solutis.votacao.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssociadoDto {
	@NotEmpty
	@NotBlank
	@NotNull
	@Size(min = 10, max = 255)
	@JsonProperty("nome")
	private String nome;

	@NotEmpty
	@NotBlank
	@NotNull
	@Email
	@Size(min = 20, max = 255)
	@JsonProperty("email")
	private String email;

	public AssociadoDto() {
	}

	public AssociadoDto(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
