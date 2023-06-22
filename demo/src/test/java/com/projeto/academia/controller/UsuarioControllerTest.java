package com.projeto.academia.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.academia.dto.UsuarioDTO;
import com.projeto.academia.mapper.MapperUsuario;
import com.projeto.academia.model.Usuario;
import com.projeto.academia.service.UsuarioServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
//@WebMvcTest(UsuarioController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

	@MockBean
	UsuarioServiceImpl service;

	@Autowired
	MockMvc mockMvc;

	Usuario usuario;

	List<Usuario> lista;
	List<UsuarioDTO> listaDTO;

	@BeforeEach
	void setUp() {
		usuario = new Usuario(1L,"paulo", "Paulo", "paulo@email.com", "Santos", "123");
		lista = List.of(new Usuario("paulo", "Paulo", "paulo@email.com", "Santos", "123"),
				new Usuario("bruno", "Bruno", "bruno@email.com", "Santos", "123"),
				new Usuario("pedro", "Pedro", "pedro@email.com", "Santos", "123"),
				new Usuario("carlos", "Carlos", "carlos@email.com", "Santos", "123"));

	}

	@Test
	void salvar() throws Exception {
			mockMvc.perform(post("/usuarios").contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(usuario)))
			.andExpect(content().string("ok"))
			.andExpect(status().isOk());
	}
	
	@Test
	void getAll() throws Exception {
		
			when(service.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(lista).map(MapperUsuario::toDTO));
			mockMvc.perform(get("/usuarios"))
					
			.andExpect(jsonPath("$.content").isArray())
			.andExpect(status().isOk());
	}
	
	@Test
	void getById() throws Exception {
			Long id=1L;
			when(service.findById(anyLong())).thenReturn(MapperUsuario.toDTO(usuario));
			mockMvc.perform(get("/usuarios/"+id))					
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(status().isOk());
	}
	
	@Test
	void deleteSucesso() throws Exception{
		Long id=1L;
		mockMvc.perform(delete("/usuarios/"+id))					
		.andExpect(content().string("{}"))
		.andExpect(status().isOk());
	}
	
	@Test
	void updateSucesso() throws Exception{
		Long id=1L;
		mockMvc.perform(put("/usuarios/"+id).contentType(MediaType.APPLICATION_JSON).content(asJsonString(usuario)))					
		.andExpect(content().string("ok"))
		.andExpect(status().isOk());
	}
	
	private static String asJsonString(Object obj) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
