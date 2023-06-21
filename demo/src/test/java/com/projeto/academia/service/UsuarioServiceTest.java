package com.projeto.academia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.projeto.academia.dto.LoginDTO;
import com.projeto.academia.dto.UsuarioDTO;
import com.projeto.academia.exception.UserNotFoundException;
import com.projeto.academia.mapper.MapperUsuario;
import com.projeto.academia.model.Usuario;
import com.projeto.academia.repository.UsuarioRepository;
import com.projeto.academia.security.Constantes;
import com.projeto.academia.security.CriadorToken;
import com.projeto.academia.security.ObjectToken;
import com.projeto.academia.security.Sessao;
@ExtendWith(SpringExtension.class)
public class UsuarioServiceTest {

	@Mock
	UsuarioRepository repository;
	@Mock
	PasswordService passwordService;
	
	
	@InjectMocks
	UsuarioServiceImpl service;
	
	
	Usuario usuario;
	List<Usuario> lista;
	@BeforeEach
	void setUp() {
		usuario=new Usuario("claudio","Claudio Luiz","claudio@gmail","Pereira","123");
		usuario.setId(1L);
		lista=List.of(new Usuario("claudio","Claudio Luiz","claudio@gmail","Pereira","123"),
				new Usuario("bruno","Bruno","bruno@email","Santos","123"),
				new Usuario("paulo","Paulo","paulo@email","Silva","123"),
				new Usuario("pedro","Pedro","pedro@email","Cardoso","123"));
	}
	
	
	
	@Test
	void salvarComSucesso() {
		repository.save(usuario);
		
		verify(repository).save(usuario);
		verifyNoMoreInteractions(repository);
	}
	
	
	@Test
	void findAllComSucesso() {
		
		List<UsuarioDTO> saidaEsperada;
		
		when(repository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(lista));
		
		Page<UsuarioDTO> saidaReal = service.findAll(PageRequest.of(0, 4));
		
		assertEquals(lista.size(), saidaReal.getContent().size());
		assertEquals(true, saidaReal.isLast());
		assertEquals(true, saidaReal.isFirst());


	}
	
	@Test
	void deleteComSucesso() {
		Long id=1L;
		
		when(repository.existsById(id)).thenReturn(true);
		service.delete(id);
		verify(repository).existsById(id);
		verify(repository).deleteById(id);
		verifyNoMoreInteractions(repository);
	}
	@Test
	void deleteThrowsUserNotFoundException() {
		Long id=1L;
		
		when(repository.existsById(id)).thenReturn(false);
		assertThrows(UserNotFoundException.class,()->service.delete(id));
		verify(repository).existsById(id);
		verifyNoMoreInteractions(repository);
	}
	
	
	@Test
	void findByIdSucesso() {
		Long id=1L;
		
		when(repository.findById(id)).thenReturn(Optional.of(usuario));
		UsuarioDTO saidaReal = service.findById(id);
		
		assertEquals(MapperUsuario.toDTO(usuario).getId(),saidaReal.getId());
		verify(repository).findById(id);
		verifyNoMoreInteractions(repository);
	}
	
	@Test
	void findByIdThrowsUserNotFoundException() {
		Long id=1L;
		
		when(repository.findById(id)).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class,()->service.findById(id));
		
		verify(repository).findById(id);
		verifyNoMoreInteractions(repository);
	}
	
	@Test
	void updateExistente() {
		
		Long id=1L;
		Usuario userNovo=new Usuario();
		
		when(repository.findById(anyLong())).thenReturn(Optional.of(usuario));
		
		service.update(userNovo, id);
		
		verify(repository).save(usuario);
		
	}
	
	@Test
	void updateNovo() {
		
		Long id=1L;
		Usuario userNovo=new Usuario();
		
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		
		service.update(userNovo, id);
		
		verify(repository).save(userNovo);
		
	}
	

}
