package com.projeto.academia.exception;

public class MensalidadePagaException extends RuntimeException{

	public MensalidadePagaException(int mes) {
		// TODO Auto-generated constructor stub
		super(String.format("A mensalidade do mes %d já foi paga",mes));
	}
	
}
