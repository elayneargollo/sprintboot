package br.com.solutis.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ServiceCpfException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ServiceCpfException(String mensagem) {
		super(mensagem);
	}
}
