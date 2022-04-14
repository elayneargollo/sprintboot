package br.com.solutis.votacao.model;

import br.com.solutis.votacao.model.enumeracao.OpcaoVoto;

public class ResultadoVotacao {
	public OpcaoVoto vencedor;
	public long quantidadeVotoNegativo;
	public long quantidadeVotoPositivo;
	public long percentualVotoNegativo;
	public long percentualVotoPositivo;
	
	public ResultadoVotacao() { };
	
	public ResultadoVotacao(OpcaoVoto vencedor, long quantidadeVotoNegativo, long quantidadeVotoPositivo,
			long percentualVotoNegativo, long percentualVotoPositivo) {
		super();
		this.vencedor = vencedor;
		this.quantidadeVotoNegativo = quantidadeVotoNegativo;
		this.quantidadeVotoPositivo = quantidadeVotoPositivo;
		this.percentualVotoNegativo = percentualVotoNegativo;
		this.percentualVotoPositivo = percentualVotoPositivo;
	}

	public OpcaoVoto getVencedor() {
		return vencedor;
	}

	public void setVencedor(OpcaoVoto vencedor) {
		this.vencedor = vencedor;
	}

	public long getQuantidadeVotoNegativo() {
		return quantidadeVotoNegativo;
	}

	public void setQuantidadeVotoNegativo(long quantidadeVotoNegativo) {
		this.quantidadeVotoNegativo = quantidadeVotoNegativo;
	}

	public long getQuantidadeVotoPositivo() {
		return quantidadeVotoPositivo;
	}

	public void setQuantidadeVotoPositivo(long quantidadeVotoPositivo) {
		this.quantidadeVotoPositivo = quantidadeVotoPositivo;
	}

	public long getPercentualVotoNegativo() {
		return percentualVotoNegativo;
	}

	public void setPercentualVotoNegativo(long percentualVotoNegativo) {
		this.percentualVotoNegativo = percentualVotoNegativo;
	}

	public long getPercentualVotoPositivo() {
		return percentualVotoPositivo;
	}

	public void setPercentualVotoPositivo(long percentualVotoPositivo) {
		this.percentualVotoPositivo = percentualVotoPositivo;
	}	
}
