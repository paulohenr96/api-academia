package com.projeto.academia.exception;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException (Long id) {
		super(String.format("Usuário com o id %d não foi encontrado.",id));
	}
	
}
