package com.projeto.academia.security;

public class Sessao {
	private String token;
	
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Sessao(String token, String username) {
		super();
		this.token = token;
		this.username = username;
	}
	
	public Sessao() {
		// TODO Auto-generated constructor stub
	}
}
