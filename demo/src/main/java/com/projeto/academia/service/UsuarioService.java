package com.projeto.academia.service;

import java.util.List;

import com.projeto.academia.dto.UsuarioDTO;
import com.projeto.academia.model.Usuario;

public interface UsuarioService {
	
	void salvar(Usuario usuario);
	List<UsuarioDTO> findAll();
	UsuarioDTO findById(Long id);
	void update(Usuario novo,Long id);
	void delete(Long id);
}
