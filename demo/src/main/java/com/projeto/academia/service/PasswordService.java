package com.projeto.academia.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class PasswordService {

	
	public String encode(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
	
	public boolean match(String passwordRaw, String passwordCriptografada) {
		return new BCryptPasswordEncoder().matches(passwordRaw, passwordCriptografada);
	}
}
