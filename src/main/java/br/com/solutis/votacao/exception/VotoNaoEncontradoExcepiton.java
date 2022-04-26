package br.com.solutis.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VotoNaoEncontradoExcepiton extends ResponseStatusException{
	
	private static final long serialVersionUID = 1L;

	public VotoNaoEncontradoExcepiton(String mensagem) {
		super(HttpStatus.BAD_REQUEST, mensagem);
	}
}
