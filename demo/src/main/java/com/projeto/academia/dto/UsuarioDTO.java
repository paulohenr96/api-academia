package com.projeto.academia.dto;

import java.util.List;
import java.util.Set;

import com.projeto.academia.model.Role;

public class UsuarioDTO {
	
	private Long id;
	
	
	private String name;
	
	private String email;
	
	private String secondName;
	
	private Set<Role> roles;
	
	
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
	
	
	
}
