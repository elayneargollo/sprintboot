package br.com.solutis.votacao.config.validacao;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VotacaoException extends ResponseStatusException{

	private static final long serialVersionUID = 1L;
	private String campo;
	private String mensagemErro;
	
	public VotacaoException(String campo, String mensagemErro) {
		super(HttpStatus.BAD_REQUEST, mensagemErro);
		this.campo = campo;
		this.mensagemErro = mensagemErro;
	}
	
	public VotacaoException(String mensagemErro) {
		super(HttpStatus.BAD_REQUEST, mensagemErro);
		this.mensagemErro = mensagemErro;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}
}
