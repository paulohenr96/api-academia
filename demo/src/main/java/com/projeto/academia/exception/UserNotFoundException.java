package com.projeto.academia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException (Long id) {
		super(String.format("Usuário com o id %d não foi encontrado.",id));
	}
	
}
