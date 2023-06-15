package com.projeto.academia.exception;

public class TokenException extends RuntimeException {

	public TokenException(String msg) {
		// TODO Auto-generated constructor stub
		super("Erro ao autenticar o token => ".concat(msg));
	}
}
