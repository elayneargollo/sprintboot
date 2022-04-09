package br.com.solutis.votacao.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank; 
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "VOTO")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Voto {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@NotEmpty @NotBlank @NotNull 
	private String descricao;
	@Value("${LocalDateTime.now()}")
	private LocalDateTime dataVotacao;
	@NotEmpty @NotBlank
	private Integer associadoId;
	@NotEmpty @NotBlank
	private Integer pautaId;
	
	public Voto() {}
	
	public Voto(Integer id, @NotEmpty @NotBlank @NotNull String descricao, LocalDateTime dataVotacao,
			Integer associadoId, Integer pautaId) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataVotacao = dataVotacao;
		this.associadoId = associadoId;
		this.pautaId = pautaId;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public LocalDateTime getDataVotacao() {
		return dataVotacao;
	}
	
	public void setDataVotacao(LocalDateTime dataVotacao) {
		this.dataVotacao = dataVotacao;
	}
	
	public Integer getIdAssociate() {
		return associadoId;
	}
	
	public void setIdAssociate(Integer associadoId) {
		this.associadoId = associadoId;
	}
	
	public Integer getIdPauta() {
		return pautaId;
	}
	public void setIdPauta(Integer pautaId) {
		this.associadoId = pautaId;
	}
}
