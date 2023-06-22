package com.projeto.academia.dto;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.academia.model.Role;

public class UsuarioDTO {
	
	private Long id;
	
	@NotNull(message = "Insira um nome")
	private String name;
	@NotNull(message = "Insira um email")
	private String email;
	@NotNull(message = "Insira um sobrenome")
	private String secondName;
	
	private Set<Role> roles;
	@NotNull(message = "Insira um username")
	private String username;
	
	@JsonIgnore
	private String password;
	
	public UsuarioDTO(Long id,String name, String email, String secondName,Set<Role> roles) {
		
		this.id=id;
		this.name = name;
		this.email = email;
		this.secondName = secondName;
		this.roles=roles;
	}
public UsuarioDTO(Long id,String name, String email, String secondName) {
		
		this.id=id;
		this.name = name;
		this.email = email;
		this.secondName = secondName;
	}
	
	public UsuarioDTO() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
