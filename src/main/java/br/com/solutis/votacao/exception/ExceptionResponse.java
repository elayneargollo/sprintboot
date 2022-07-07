package br.com.solutis.votacao.exception;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Date timestamp;
	private String messagem;
	private String detalhes;
	
	public ExceptionResponse(Date timestamp, String messagem, String detalhes) {
		super();
		this.timestamp = timestamp;
		this.messagem = messagem;
		this.detalhes = detalhes;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessagem() {
		return messagem;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
