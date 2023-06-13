package com.projeto.academia.model;

import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

import com.projeto.academia.enumeration.Pacote;

@Entity
@DiscriminatorColumn(name = "aluno")
public class Aluno extends Usuario {

	private Pacote pacote;
	private boolean inside;

	
	Aluno (){
		Role role = new Role("ROLE_ALUNO");
		role.setId(2L);
		Set<Role> set = Set.of(role);
		super.setRoles(set);
	}
	public Pacote getPacote() {
		return pacote;
	}

	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}

	public boolean isInside() {
		return inside;
	}

	public void setInside(boolean inside) {
		this.inside = inside;
	}
}
