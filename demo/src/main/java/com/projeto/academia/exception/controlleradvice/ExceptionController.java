package com.projeto.academia.exception.controlleradvice;

import java.util.List;
import java.util.stream.Collectors;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.projeto.academia.exception.MensalidadePagaException;
import com.projeto.academia.exception.TratarErros;
import com.projeto.academia.exception.UserNotFoundException;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		List<String> erros = ex.getBindingResult().getFieldErrors().stream().map(erro -> erro.getDefaultMessage())
				.collect(Collectors.toList());

		return new ResponseEntity<>(new TratarErros(status, erros), status);
	}
	
	@ExceptionHandler (UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex,
			 WebRequest request){
		
		
		return new ResponseEntity<>(new TratarErros(HttpStatus.NOT_FOUND, ex.getMessage()),HttpStatus.NOT_FOUND);
			
	}
	
	@ExceptionHandler (UsernameNotFoundException.class)
	public ResponseEntity<Object> handUserNameNotFound(UsernameNotFoundException ex,
			 WebRequest request){
		
		return new ResponseEntity<>(new TratarErros(HttpStatus.NOT_FOUND, ex.getMessage()),HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler (MalformedJwtException.class)
	public ResponseEntity<Object> handleMalformedJwtException(MalformedJwtException ex,
			 WebRequest request){
		
		return new ResponseEntity<>(new TratarErros(HttpStatus.NOT_FOUND, ex.getMessage()),HttpStatus.UNAUTHORIZED);

	}
	
	@ExceptionHandler (MensalidadePagaException.class)
	public ResponseEntity<Object> handleMensalidadePagaException(MensalidadePagaException ex,
			 WebRequest request){
		
		
		return new ResponseEntity<>(
				new TratarErros(HttpStatus.CONFLICT, ex.getMessage())
				,HttpStatus.CONFLICT);
			
	}
	@ExceptionHandler (IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex,
			 WebRequest request){
		
		
		return new ResponseEntity<>(
				new TratarErros(HttpStatus.NOT_ACCEPTABLE, ex.getMessage())
				,HttpStatus.NOT_ACCEPTABLE);
			
	}
	
	@ExceptionHandler (PSQLException.class)
	public ResponseEntity<Object> handlePSQLException(PSQLException ex,
			 HttpStatus status, WebRequest request){
		
		return new ResponseEntity<>(
				new TratarErros(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage())
				,HttpStatus.INTERNAL_SERVER_ERROR);
			
	}

}
