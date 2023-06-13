package com.projeto.academia.dto;

import javax.validation.constraints.NotNull;

public class LoginDTO {
	@NotNull(message = "Por favor insira um login.")
	private String username;
	
	@NotNull(message = "Por favor insira uma senha.")
	private String password;
	
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
