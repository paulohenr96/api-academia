package com.projeto.academia.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class WelcomeController {

	@PostMapping
	public String home() {
		return "Bem vindo à minha api. Para efetuar o login acesse /login";
	}
}
