package br.com.solutis.votacao.model.dto;

import br.com.solutis.votacao.model.Associado;

public class AssociadoDto {
	private String nome;
	private String email;
	
	public AssociadoDto() {}

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
	
	public AssociadoDto converterByAssociadoDto(Associado associado)
	{
		return new AssociadoDto(associado.getEmail(), associado.getEmail());
	}
	
	public Associado converterByAssociado(AssociadoDto associadoDto)
	{
		return new Associado(associadoDto.getNome(), associadoDto.getEmail());
	}
	
}
