package br.com.solutis.votacao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "pauta")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Pauta {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	private Integer idSessao;
	private Long QuantidadeVotoPorGrupo;
	private Long QuantidadeVotoPercentualPorGrupo;
	private Long Vencedor;
	
	
}
