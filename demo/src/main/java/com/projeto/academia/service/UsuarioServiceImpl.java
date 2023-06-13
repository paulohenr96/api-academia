package com.projeto.academia.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.projeto.academia.dto.UsuarioDTO;
import com.projeto.academia.exception.UserNotFoundException;
import com.projeto.academia.model.Role;
import com.projeto.academia.model.Usuario;
import com.projeto.academia.repository.UsuarioRepository;
import com.projeto.academia.security.Constantes;
import com.projeto.academia.security.CriadorToken;
import com.projeto.academia.security.LoginDTO;
import com.projeto.academia.security.ObjectToken;
import com.projeto.academia.security.Sessao;

@Service
@Component
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordService passwordService;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordService passwordService) {
		// TODO Auto-generated constructor stub
		this.usuarioRepository = usuarioRepository;
		this.passwordService = passwordService;
	}

	@Override
	public void salvar(Usuario usuario) {

		usuarioRepository.save(usuario);
	}

	@Override
	public List<UsuarioDTO> findAll() {

		return usuarioRepository.findAll().stream().map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getName(),
				usuario.getEmail(), usuario.getSecondName(), usuario.getRoles())).collect(Collectors.toList());
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

		try {
			usuarioRepository.deleteById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UserNotFoundException(id);
		}

	}

	@Override
	public UsuarioDTO findById(Long id) {
		// TODO Auto-generated method stub

		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

		return new UsuarioDTO(usuario.getId(), usuario.getName(), usuario.getEmail(), usuario.getSecondName(),
				usuario.getRoles());
	}

	@Override
	public void update(Usuario usuarioNovo, Long id) {
		// TODO Auto-generated method stub
		usuarioRepository.findById(id).map(usuario -> {
			usuario.setName(usuarioNovo.getName());
			usuario.setSecondName(usuarioNovo.getSecondName());
			usuario.setEmail(usuarioNovo.getEmail());
			return usuarioRepository.save(usuario);
		}).orElseGet(() -> {
			return usuarioRepository.save(usuarioNovo);
		});

	}

	public Sessao logar(LoginDTO login) {
		Sessao sessao = new Sessao();

		Usuario usuario = usuarioRepository.loadByUserName(login.getUsername());
		if (usuario == null || !passwordService.match(login.getPassword(), usuario.getPassword())) {
			throw new UsernameNotFoundException("Login ou senha incorreto.");
		}
		sessao.setUsername(usuario.getUsername());

		ObjectToken objetoToken = new ObjectToken();

		objetoToken.setDataFinal(new Date(System.currentTimeMillis() + Constantes.DURACAO_TOKEN));
		objetoToken.setDataInicial(new Date());
		objetoToken.setRoles(usuario.getRoles().stream().map(Role::getRole).collect(Collectors.toList()));

		sessao.setToken(CriadorToken.criarToken(Constantes.PREFIXO_TOKEN, Constantes.PALAVRA_SECRETA, objetoToken));

		return sessao;
	}

}
