package br.com.solutis.votacao.model;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Value;
import br.com.solutis.votacao.model.dto.AssociadoDto;
import br.com.solutis.votacao.model.enumeracao.OpcaoVoto;
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
	
	@Enumerated(EnumType.STRING)
	private OpcaoVoto descricao;
	
	@Value("${LocalDateTime.now()}")
	private LocalDateTime dataVotacao;
	
	private Integer associadoId;
	private Integer pautaId;
	
	public Voto() {}

	public Voto(Integer id, OpcaoVoto descricao, Integer associadoId, Integer pautaId) {
		this.id = id;
		this.descricao = descricao;
		this.associadoId = associadoId;
		this.pautaId = pautaId;
		this.dataVotacao = LocalDateTime.now();
	}
	
	public Voto(OpcaoVoto descricao, Integer associadoId, Integer pautaId) {
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

	@Override
	public int hashCode() {
		return Objects.hash(associadoId, dataVotacao, descricao, id, pautaId);
	}	
	
	public Associado converterByAssociado(AssociadoDto associadoDto)
	{
		return new Associado(associadoDto.getNome(), associadoDto.getEmail());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		return Objects.equals(associadoId, other.associadoId) && Objects.equals(dataVotacao, other.dataVotacao)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id)
				&& Objects.equals(pautaId, other.pautaId);
	}

	@Override
	public String toString() {
		return "Voto [id=" + id + ", descricao=" + descricao + ", dataVotacao=" + dataVotacao + ", associadoId="
				+ associadoId + ", pautaId=" + pautaId + ", getId()=" + getId() + ", getDescricao()=" + getDescricao()
				+ ", getDataVotacao()=" + getDataVotacao() + ", getAssociadoId()=" + getAssociadoId()
				+ ", getPautaId()=" + getPautaId() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}
}
