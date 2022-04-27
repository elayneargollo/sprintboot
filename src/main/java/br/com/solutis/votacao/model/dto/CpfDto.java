package br.com.solutis.votacao.model.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class CpfDto {
	String cpf;
	Boolean isValid;
	
	public CpfDto(String cpf, Boolean isValid) {
		this.cpf = cpf;
		this.isValid = isValid;
	}
	
	public CpfDto() {
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
}
