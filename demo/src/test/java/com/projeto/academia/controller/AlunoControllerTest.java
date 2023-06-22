package com.projeto.academia.controller;

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

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.academia.dto.AlunoDTO;
import com.projeto.academia.dto.MensalidadeDTO;
import com.projeto.academia.model.Aluno;
import com.projeto.academia.repository.AlunoRepository;
import com.projeto.academia.repository.MensalidadeRepository;
import com.projeto.academia.service.AlunoService;

//@WebMvcTest(controllers =  AlunoController.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AlunoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AlunoRepository repository;

	@MockBean
	private MensalidadeRepository mensalidadeRepository;

	@MockBean
	private AlunoService service;

//	@InjectMocks
//	AlunoController controller;
//	
	Aluno aluno;
	List<Aluno> lista;
	List<AlunoDTO> listaDTO;

	List<MensalidadeDTO> listaMensalidades;
	@BeforeEach
	void setUp() {
		
		aluno=new Aluno("Paulo", "Henrique");
		
		lista = List.of(new Aluno("Paulo", "Henrique"), new Aluno("Pedro", "Henrique"),
				new Aluno("Claudio", "Henrique"), new Aluno("Bruno", "Henrique"));
		
		listaDTO = List.of(new AlunoDTO("Paulo", "Henrique"), new AlunoDTO("Pedro", "Henrique"),
				new AlunoDTO("Claudio", "Henrique"), new AlunoDTO("Bruno", "Henrique"));
		
		
		listaMensalidades=List.of(new MensalidadeDTO(1L),
				new MensalidadeDTO(2L),
				new MensalidadeDTO(3L));
	}

	@Test
	void deleteAlunoSucesso() throws Exception {
		Long id = 1L;
		when(service.removerAluno(id)).thenReturn("{}");
		mockMvc.perform(delete("/alunos" + "/" + id)).andExpect(content().string("{}")).andExpect(status().isOk());
	}

	@Test
	void getAllSucesso() throws Exception {

		when(service.findAllAluno(anyInt(), anyInt())).thenReturn(new PageImpl<>(lista));

		mockMvc.perform(get("/alunos")
				.param("page", "0")
				.param("size", "4"))
		.andExpect(jsonPath("$.content")
				.isArray())
		.andExpect(status()
				.isOk());
	}

	@Test
	void getAlunoSucesso() throws Exception{
		Long id=1L;
		when(service.findAlunoById(id)).thenReturn(new AlunoDTO(1L));
		mockMvc.perform(get("/alunos/"+id)).andExpect(jsonPath("$.id").value("1"));
	}
	
	@Test
	void salvarAlunoTest() throws Exception{
		AlunoDTO alunoDTO=new AlunoDTO("Paulo","Santos");
		
		mockMvc.perform(post("/alunos").contentType(MediaType.APPLICATION_JSON).content(asJsonString(alunoDTO)))
		.andExpect(content().string("ok")).andExpect(status().isCreated());
	}
	
	@Test
	void pagarMensalidadeTest() throws Exception{
		MensalidadeDTO mensalidadeDTO=new MensalidadeDTO();
		mensalidadeDTO.setId(1L);
		mensalidadeDTO.setData(new Date());

		Long aluno=2L;
		mockMvc.perform(post("/alunos"+"/"+aluno+"/mensalidade")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(mensalidadeDTO)))
		.andExpect(content().string("ok")).andExpect(status().isOk());
	}
	
	@Test
	void todasMensalidadesAluno() throws Exception{
		Long aluno=1L;
		when(service.findAllMensalidadeAluno(anyLong())).thenReturn(listaMensalidades);
		
		mockMvc.perform(get("/alunos"+"/"+aluno+"/mensalidade"))
		.andExpect(jsonPath("$").isArray()).andExpect(status().isOk());
	}
	
	@Test
	void deleteMensalidadeAluno() throws Exception{
		Long mensalidade=1L;
		when(service.deleteMensalidadeAluno(anyLong())).thenReturn("{}");
		
		mockMvc.perform(delete("/alunos"+"/mensalidade/"+mensalidade))
		.andExpect(content().string("{}")).andExpect(status().isOk());
	}
	
	
	@Test
	void atualizarAlunoSucesso() throws Exception{
		
		when(service.atualizarAluno(aluno)).thenReturn("ok");
		
		mockMvc.perform(put("/alunos")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(aluno)))
		.andExpect(content().string("ok")).andExpect(status().isOk());
	}
	
	@Test
	void getAllAlunosDevedoresSucesso() throws Exception{
		int mes=1;
		when(service.findAllAlunosDevedores(mes,0,4)).thenReturn(new PageImpl<AlunoDTO>(listaDTO));
		
		mockMvc.perform(get("/alunos/devedores/"+mes).param("page", "0").param("size", "4"))
		.andExpect(jsonPath("$.content").isArray()).andExpect(status().isOk());
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
