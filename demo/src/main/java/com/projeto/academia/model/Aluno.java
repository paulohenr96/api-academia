package com.projeto.academia.model;

import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel("Aluno matriculado na academia")
public class Aluno {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String secondName;
	
	private Date dataDeNascimento;
	
	private Date dataDeMatricula;
	
	@JoinTable(name = "aluno_mensalidade_paga", joinColumns = @JoinColumn(name = "aluno_id"), inverseJoinColumns = @JoinColumn(name = "mensalidade_id"))
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE,orphanRemoval = true)
	private List<Mensalidade> mensalidadePaga;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSecondName() {
		return secondName;
	}



	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}



	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}



	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}



	public Date getDataDeMatricula() {
		return dataDeMatricula;
	}



	public void setDataDeMatricula(Date dataDeMatricula) {
		this.dataDeMatricula = dataDeMatricula;
	}



	public List<Mensalidade> getMensalidadePaga() {
		return mensalidadePaga;
	}



	public void setMensalidadePaga(List<Mensalidade> mensalidadePaga) {
		this.mensalidadePaga = mensalidadePaga;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(id, other.id);
	}



	
	
	
	
}
