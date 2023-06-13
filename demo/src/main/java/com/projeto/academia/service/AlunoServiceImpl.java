package com.projeto.academia.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;

import com.projeto.academia.dto.UsuarioDTO;
import com.projeto.academia.exception.UserNotFoundException;
import com.projeto.academia.model.Role;
import com.projeto.academia.model.Usuario;
import com.projeto.academia.repository.UsuarioRepository;

@Controller
public class AlunoServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordService passwordService;
	public AlunoServiceImpl(UsuarioRepository usuarioRepository,PasswordService passwordService) {
		// TODO Auto-generated constructor stub
		this.usuarioRepository = usuarioRepository;
		this.passwordService = passwordService;

	}

	@Override
	public void salvar(Usuario aluno) {
		// TODO Auto-generated method stub
		Role role = new Role("ROLE_ALUNO");
		role.setId(1L);
		Set<Role> set = Set.of(role);
		aluno.setRoles(set);
		aluno.setPassword(passwordService.encode(aluno.getPassword()));
		usuarioRepository.save(aluno);
	}

	@Override
	public List<UsuarioDTO> findAll() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAllAlunos().stream().map(usuario -> new UsuarioDTO(usuario.getId(),
				usuario.getName(), usuario.getEmail(), usuario.getSecondName())).collect(Collectors.toList());
	}

	@Override
	public UsuarioDTO findById(Long id) {
		Usuario usuario = usuarioRepository.findAlunoById(id).orElseThrow(() -> new UserNotFoundException(id));

		return new UsuarioDTO(usuario.getId(), usuario.getName(), usuario.getEmail(), usuario.getSecondName());
	}

	@Override
	public void update(Usuario novo, Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}
}
