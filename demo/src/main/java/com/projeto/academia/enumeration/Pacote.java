package com.projeto.academia.enumeration;

import java.math.BigDecimal;

public enum Pacote {
	MENSAL(30,new BigDecimal(50)),
	TRIMESTRAL(90,new BigDecimal(130)),
	SEMESTRAL(180,new BigDecimal(240)),
	ANUAL(365,new BigDecimal(460));
	
	
	private final int dias;
	private final BigDecimal valor;
	
	
	
	private Pacote(int dias, BigDecimal valor) {
		this.dias = dias;
		this.valor = valor;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public int getDias() {
		return dias;
	}
}
