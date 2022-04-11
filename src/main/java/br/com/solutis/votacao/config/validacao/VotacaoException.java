package br.com.solutis.votacao.config.validacao;

public class VotacaoException {
	
	private String campo;
	private String mensagemErro;
	
	public VotacaoException() {}
	
	public VotacaoException(String campo, String mensagemErro) {
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
