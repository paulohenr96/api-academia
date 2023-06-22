package com.projeto.academia.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class AlunoDTO {

	private Long id;

	@NotNull(message = "Insira o name")
	private String name;

	@NotNull(message = "Insira o secondName")
	private String secondName;

	@NotNull(message = "Insira a dataDeNascimento")
	private Date dataDeNascimento;

	@NotNull(message = "Insira a data de matricula")
	private Date dataDeMatricula;

	public AlunoDTO() {
	}

	public AlunoDTO(Long id) {
		super();
		this.id = id;
	}

	public AlunoDTO(@NotNull(message = "Insira o name") String name,
			@NotNull(message = "Insira o secondName") String secondName) {
		super();
		this.name = name;
		this.secondName = secondName;
	}

	public AlunoDTO(@NotNull(message = "Insira o name") String name,
			@NotNull(message = "Insira o secondName") String secondName,
			@NotNull(message = "Insira a dataDeNascimento") Date dataDeNascimento,
			@NotNull(message = "Insira a data de matricula") Date dataDeMatricula) {
		super();
		this.name = name;
		this.secondName = secondName;
		this.dataDeNascimento = dataDeNascimento;
		this.dataDeMatricula = dataDeMatricula;
	}

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

}
