package br.com.solutis.votacao.model.entity;

import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Entity
@Table(name = "ASSOCIADO")
@Data
public class Associado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@NotEmpty @NotBlank @NotNull @Size(min=10, max=255) 
	private String nome;
	
	@NotEmpty @NotBlank @NotNull @Email @Size(min=20, max=255)
	private String email;
	
	private String cpf;
	
	public Associado() {}
	
	public Associado(Integer id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public Associado (String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
		
	public Associado(@NotEmpty @NotBlank @NotNull @Size(min = 10, max = 255) String nome,
			@NotEmpty @NotBlank @NotNull @Email @Size(min = 20, max = 255) String email, @CPF String cpf) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
	}

	public Associado(Integer id, @NotEmpty @NotBlank @NotNull @Size(min = 10, max = 255) String nome,
			@NotEmpty @NotBlank @NotNull @Email @Size(min = 20, max = 255) String email, String cpf) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Associado other = (Associado) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Associado [id=" + id + ", nome=" + nome + ", email=" + email + "]";
	}
}
