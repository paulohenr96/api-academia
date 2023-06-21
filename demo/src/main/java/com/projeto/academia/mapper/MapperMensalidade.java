package com.projeto.academia.mapper;

import com.projeto.academia.dto.MensalidadeDTO;
import com.projeto.academia.model.Mensalidade;

public class MapperMensalidade {

	public static MensalidadeDTO toDTO(Mensalidade mensalidade) {
		MensalidadeDTO mensalidadeDTO=new MensalidadeDTO();
		
		if (mensalidade.getId()!=null) {
			mensalidadeDTO.setId(mensalidade.getId());
		}
		if (mensalidade.getDataPagamento()!=null) {
			mensalidadeDTO.setData(mensalidade.getDataPagamento());
		}
		return mensalidadeDTO;
		
	}
}
