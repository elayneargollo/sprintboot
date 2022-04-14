package br.com.solutis.votacao.model.viewModel;

import java.util.Objects;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AssociadoViewModel {
	private Integer id;
	
	@NotEmpty @NotBlank @NotNull @Size(min=10, max=255) 
	private String nome;
	
	@NotEmpty @NotBlank @NotNull @Email @Size(min=20, max=255)
	private String email;
	
	public AssociadoViewModel() {}
	
	public AssociadoViewModel(Integer id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public AssociadoViewModel (String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(email, id, nome);
	}
}
