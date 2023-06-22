package com.projeto.academia.service;

import java.util.List;
import java.util.Optional;
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

@Component
public class UsuarioDetailsService implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
			
	
		
		return usuarioRepository.loadByUserName(username)
				.map(u -> new User(u.getUsername(),u.getPassword(),u.getRoles()
						.stream()
						.map(role -> new SimpleGrantedAuthority(role.getRole()))
						.collect(Collectors.toList())))
		.orElseThrow(()->new UsernameNotFoundException(username));
	}

}
