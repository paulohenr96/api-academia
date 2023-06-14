package com.projeto.academia.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class MensalidadeDTO {
	
	private Long id;
	
	@NotNull(message = "Insira uma data para pagamento.")
	private Date data;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public MensalidadeDTO(Long id,Date data) {
		// TODO Auto-generated constructor stub
		this.data = data;
		this.id=id;

	}
	public MensalidadeDTO() {
		// TODO Auto-generated constructor stub

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
