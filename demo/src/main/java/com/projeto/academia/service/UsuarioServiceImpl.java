package com.projeto.academia.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.projeto.academia.dto.LoginDTO;
import com.projeto.academia.dto.UsuarioDTO;
import com.projeto.academia.exception.UserNotFoundException;
import com.projeto.academia.mapper.MapperUsuario;
import com.projeto.academia.model.Role;
import com.projeto.academia.model.Usuario;
import com.projeto.academia.repository.UsuarioRepository;
import com.projeto.academia.security.Constantes;
import com.projeto.academia.security.CriadorToken;
import com.projeto.academia.security.ObjectToken;
import com.projeto.academia.security.Sessao;

@Service
@Component
public class UsuarioServiceImpl {

	private final UsuarioRepository usuarioRepository;
	private final PasswordService passwordService;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordService passwordService) {
		// TODO Auto-generated constructor stub
		this.usuarioRepository = usuarioRepository;
		this.passwordService = passwordService;
	}

	public void salvar(Usuario usuario) {

		usuarioRepository.save(usuario);
	}

	public Page<UsuarioDTO> findAll(PageRequest of) {

		return usuarioRepository.findAll(of).map(MapperUsuario::toDTO);
	}

	public void delete(Long id) {
		if (!usuarioRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		usuarioRepository.deleteById(id);
		
	}

	public UsuarioDTO findById(Long id) {
		// TODO Auto-generated method stub

		return usuarioRepository.findById(id).map(MapperUsuario::toDTO)
				.orElseThrow(() -> new UserNotFoundException(id));
	}

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

//		usuarioRepository.loadByUserName(login.getUsername()).stream().map(u-> new Sessao())
//		usuarioRepository.loadByUserName(login.getUsername()).map(u->new Sessao())

		Optional<Usuario> loadByUserName = usuarioRepository.loadByUserName(login.getUsername());
		if (loadByUserName.isEmpty()
				|| !passwordService.match(login.getPassword(), loadByUserName.get().getPassword())) {
			throw new UsernameNotFoundException("Login ou senha incorreto.");
		}
		Usuario usuario = loadByUserName.get();
		sessao.setUsername(usuario.getUsername());

		ObjectToken objetoToken = new ObjectToken();

		objetoToken.setDataFinal(new Date(System.currentTimeMillis() + Constantes.DURACAO_TOKEN));
		objetoToken.setDataInicial(new Date());
		objetoToken.setRoles(usuario.getRoles().stream().map(Role::getRole).collect(Collectors.toList()));

		sessao.setToken(CriadorToken.criarTokenNovo(objetoToken));

		return sessao;
	}

}
