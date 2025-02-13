package com.projeto.academia.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
//@ApiModel("Mensalidade do aluno")
public class Mensalidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;
	
	
	

	public Mensalidade(Date dataPagamento, Aluno aluno) {
		this.dataPagamento = dataPagamento;
		this.aluno = aluno;
	}
	public Mensalidade() {
		
	}

	@ManyToOne
	private Aluno aluno;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
}
