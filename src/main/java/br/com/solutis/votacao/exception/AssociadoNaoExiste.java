package br.com.solutis.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import br.com.solutis.votacao.config.validacao.IVotacaoException;

public class AssociadoNaoExiste extends ResponseStatusException implements IVotacaoException{

	private static final long serialVersionUID = 1L;
	
	public AssociadoNaoExiste(String mensagem) {
		super(HttpStatus.NOT_FOUND);
	}	
}
