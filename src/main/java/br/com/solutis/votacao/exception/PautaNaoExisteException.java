package br.com.solutis.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PautaNaoExisteException extends ResponseStatusException{

	private static final long serialVersionUID = 1L;

	public PautaNaoExisteException(String mensagem) {
		super(HttpStatus.BAD_REQUEST, mensagem);
	}
}
