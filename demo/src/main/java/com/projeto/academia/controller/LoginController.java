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

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@Tag(name = "Login", description = "Endpoints for user authentication")  // ✅ Defines the section in Swagger
public class LoginController {

    private final UsuarioServiceImpl service;

    LoginController(UsuarioServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/login")
    @Operation(summary = "User Authentication", description = "Authenticates a user who is not using a token")  // ✅ Swagger operation description
    public ResponseEntity<Sessao> logar(@Valid @RequestBody LoginDTO login) {
        return new ResponseEntity<>(service.logar(login), HttpStatus.OK);
    }
}
