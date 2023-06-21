package com.projeto.academia.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.academia.dto.LoginDTO;
import com.projeto.academia.security.Sessao;
import com.projeto.academia.service.UsuarioServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags="Realizar o login")
public class LoginController {

	
	private final UsuarioServiceImpl service;
	
	LoginController (UsuarioServiceImpl service){
		this.service=service;
	}
	
	
	@PostMapping("/login")@ApiOperation("Efetua a autenticação do usuário que não está usando token")
	ResponseEntity<Sessao> logar(@Valid @RequestBody LoginDTO login){return new ResponseEntity(service.logar(login), HttpStatus.OK);}
	
}
