package com.projeto.academia.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;

import com.projeto.academia.dto.UsuarioDTO;
import com.projeto.academia.exception.UserNotFoundException;
import com.projeto.academia.model.Aluno;
import com.projeto.academia.model.Role;
import com.projeto.academia.model.Usuario;
import com.projeto.academia.repository.UsuarioRepository;

@Controller
public class AlunoServiceImpl  {

	private final UsuarioRepository usuarioRepository;
	private final PasswordService passwordService;
	public AlunoServiceImpl(UsuarioRepository usuarioRepository,PasswordService passwordService) {
		// TODO Auto-generated constructor stub
		this.usuarioRepository = usuarioRepository;
		this.passwordService = passwordService;

	}

	public void salvar(Aluno aluno) {
		// TODO Auto-generated method stub
		
		aluno.setPassword(passwordService.encode(aluno.getPassword()));
		usuarioRepository.save(aluno);
	}

	public List<UsuarioDTO> findAll() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAllAlunos().stream().map(usuario -> new UsuarioDTO(usuario.getId(),
				usuario.getName(), usuario.getEmail(), usuario.getSecondName())).collect(Collectors.toList());
	}

	public UsuarioDTO findById(Long id) {
		Usuario usuario = usuarioRepository.findAlunoById(id).orElseThrow(() -> new UserNotFoundException(id));

		return new UsuarioDTO(usuario.getId(), usuario.getName(), usuario.getEmail(), usuario.getSecondName());
	}

	public void update(Usuario novo, Long id) {
		// TODO Auto-generated method stub
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub

	}
}
