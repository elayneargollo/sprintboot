package br.com.solutis.votacao.config;

public class VotacaoException {
	
	private String campo;
	private String mensagemErro;
	
	public VotacaoException(String campo, String mensagemErro) {
		super();
		this.campo = campo;
		this.mensagemErro = mensagemErro;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}
}
