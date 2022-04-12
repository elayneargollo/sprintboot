package br.com.solutis.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AssociadoNaoExiste extends ResponseStatusException{

	private static final long serialVersionUID = 1L;
	
	public AssociadoNaoExiste(String mensagem) {
		super(HttpStatus.NOT_FOUND, mensagem);
	}

}
