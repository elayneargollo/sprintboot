package br.com.solutis.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.solutis.votacao.config.validacao.IVotacaoException;

public class PautaNaoAbertaException extends ResponseStatusException implements IVotacaoException{

	private static final long serialVersionUID = 1L;

	public PautaNaoAbertaException(String mensagem) {
		super(HttpStatus.BAD_REQUEST);
	}
}
