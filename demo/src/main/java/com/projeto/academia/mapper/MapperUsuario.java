package com.projeto.academia.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.projeto.academia.dto.UsuarioDTO;
import com.projeto.academia.model.Usuario;

public class MapperUsuario {

	
	public static UsuarioDTO toDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO=new UsuarioDTO();
		
		if (usuario.getId()!=null) {
			usuarioDTO.setId(usuario.getId());
		}
		if (usuario.getName()!=null) {
			usuarioDTO.setName(usuario.getName());
		}
		if (usuario.getSecondName()!=null) {
			usuarioDTO.setSecondName(usuario.getSecondName());
		}
		if (usuario.getUsername()!=null) {
			usuarioDTO.setUsername(usuario.getUsername());
		}
		if (usuario.getEmail()!=null) {
			usuarioDTO.setEmail(usuario.getEmail());
		}
		if (usuario.getRoles()!=null) {
			usuarioDTO.setRoles(usuario.getRoles());
		}
		
		
		
		return usuarioDTO;
	}

	
	public static Usuario toUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario=new Usuario();
		
		if (usuarioDTO.getId()!=null) {
			usuario.setId(usuario.getId());
		}
		if (usuarioDTO.getName()!=null) {
			usuario.setName(usuario.getName());
		}
		if (usuarioDTO.getSecondName()!=null) {
			usuario.setSecondName(usuario.getSecondName());
		}
		if (usuarioDTO.getUsername()!=null) {
			usuario.setUsername(usuario.getUsername());
		}
		if (usuarioDTO.getRoles()!=null) {
			usuario.setRoles(usuario.getRoles());
		}
		
		
		
		return usuario;
	}
	
	public static Usuario toUsuarioPassword(UsuarioDTO usuarioDTO) {
		Usuario usuario = toUsuario(usuarioDTO);
		if (usuarioDTO.getPassword()!=null) {
			usuario.setPassword(new BCryptPasswordEncoder().encode(usuarioDTO.getPassword()));

		}
		
		return usuario;
	}
}
