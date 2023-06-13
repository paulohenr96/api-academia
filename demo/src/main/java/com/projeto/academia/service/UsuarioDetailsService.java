package com.projeto.academia.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.projeto.academia.model.Usuario;
import com.projeto.academia.repository.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 Usuario usuario = usuarioRepository.loadByUserName(username);
		 System.out.println(usuario);
			if (usuario == null) {
				throw new UsernameNotFoundException(username);
			}	
		List<SimpleGrantedAuthority> collect =usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole()))
				.collect(Collectors.toList());
													
		collect.forEach(c->System.out.println(c.getAuthority()));
		UserDetails userDetails = new User(username, usuario.getPassword(), collect);
		
		return userDetails;
	}

}
