package com.projeto.academia.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
//@ApiModel("Classe do usuário que irá utilizar o sistema")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String username;

	private String name;

	private String email;

	private String secondName;

	private String password;

	@JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;

	public Usuario(String username, String name, String email, String secondName, String password) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.secondName = secondName;
		this.password = password;
	}

	public Usuario() {
	}

	public Usuario(Long l, String username, String name, String email, String secondName, String password) {
		// TODO Auto-generated constructor stub
		this.id = l;

		this.username = username;
		this.name = name;
		this.email = email;
		this.secondName = secondName;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
