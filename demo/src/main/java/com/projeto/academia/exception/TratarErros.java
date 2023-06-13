package com.projeto.academia.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class TratarErros {
	private HttpStatus status;
	
	private List<String> erros;
	
	public TratarErros(HttpStatus status, List<String> erros) {
		this.setStatus(status);
		this.setErros(erros);
	}
	
	public TratarErros(HttpStatus status, String erros) {
		this.setStatus(status);
		this.setErros(Arrays.asList(erros));
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}
	
	
	
}
