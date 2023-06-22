package com.projeto.academia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.projeto.academia.dto.AlunoDTO;
import com.projeto.academia.dto.MensalidadeDTO;
import com.projeto.academia.exception.MensalidadePagaException;
import com.projeto.academia.exception.UserNotFoundException;
import com.projeto.academia.mapper.MapperAluno;
import com.projeto.academia.model.Aluno;
import com.projeto.academia.model.Mensalidade;
import com.projeto.academia.repository.AlunoRepository;
import com.projeto.academia.repository.MensalidadeRepository;

@ExtendWith(SpringExtension.class)
public class AlunoServiceTest {

	@Mock
	AlunoRepository repository;

	@Mock
	MensalidadeRepository mensalidadeRepository;
	
	@InjectMocks
	AlunoService service;

	Aluno aluno;

	List<Aluno> lista;

	@BeforeEach
	void setUp() {
		aluno = new Aluno();
		aluno.setId(1L);
		aluno.setName("João");
		aluno.setSecondName("Silva");

		lista = new ArrayList<Aluno>();

		lista.add(new Aluno("Paulo", "Santos"));
		lista.add(new Aluno("Claudio", "Ferreira"));
		lista.add(new Aluno("Pedro", "Roberto"));
		lista.add(new Aluno("Fernando", "Rodrigues"));

	}

	@Test
	void salvarAlunoSucesso() {

		service.salvarAluno(aluno);

		verify(repository).save(aluno);
		verifyNoMoreInteractions(repository);
	}

	@Test
	void findAllSucesso() {

		when(repository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<Aluno>(lista));

		Page<Aluno> saida = service.findAllAluno(0, 4);
		assertEquals(lista.size(), saida.getContent().size());

	}

	@Test
	void findByIdSucesso() {

		when(repository.findById(anyLong())).thenReturn(Optional.of(aluno));

		AlunoDTO saida = service.findAlunoById(1L);
		assertEquals(aluno.getId(), saida.getId());

	}
	
	@Test
	void findByIdUserNotFoundException() {

		when(repository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class,()->service.findAlunoById(1L));

	}
	
	@Test
	void pagarMensalidadeSucesso() {
		Long id = 2L;
		MensalidadeDTO mens= new MensalidadeDTO();
		Date date = new Date();
		Date date2 = new Date();

		try {
			date=new SimpleDateFormat("dd/MM/yyyy").parse("20/10/2021");
			date2=new SimpleDateFormat("dd/MM/yyyy").parse("20/11/2021");

		}catch (Exception e) {
			
		}
		
		mens.setData(date);
		
		Mensalidade mensalidade=new Mensalidade();
		mensalidade.setDataPagamento(date2);
		
		when(mensalidadeRepository.mensalidadesAluno(anyLong())).thenReturn(List.of(mensalidade));
		when(repository.findById(id)).thenReturn(Optional.of(aluno));
		
		service.pagarMensalidade(id, mens);
		
		verify(mensalidadeRepository).save(any(Mensalidade.class));
	}
	
	@Test
	void pagarMensalidadeThrowsMensalidadePagaException() {
		Long id = 2L;
		MensalidadeDTO mens= new MensalidadeDTO();
		Date date = new Date();
		Date date2 = new Date();

		try {
			date=new SimpleDateFormat("dd/MM/yyyy").parse("20/10/2021");
			date2=date;

		}catch (Exception e) {
			
		}
		
		mens.setData(date);
		
		Mensalidade mensalidade=new Mensalidade();
		mensalidade.setDataPagamento(date2);
		
		when(mensalidadeRepository.mensalidadesAluno(anyLong())).thenReturn(List.of(mensalidade));
		when(repository.findById(id)).thenReturn(Optional.of(aluno));
		
		assertThrows(MensalidadePagaException.class,()->service.pagarMensalidade(id, mens));
		
	}
	@Test
	void pagarMensalidadeSucessoThrowUserNotFoundException() {
		Long id = 2L;
		MensalidadeDTO mens= new MensalidadeDTO();
		Date date = new Date();
		Date date2 = new Date();

		try {
			date=new SimpleDateFormat("dd/MM/yyyy").parse("20/10/2021");
			date2=new SimpleDateFormat("dd/MM/yyyy").parse("20/11/2021");

		}catch (Exception e) {
			
		}
		
		mens.setData(date);
		
		Mensalidade mensalidade=new Mensalidade();
		mensalidade.setDataPagamento(date2);
		
		when(mensalidadeRepository.mensalidadesAluno(anyLong())).thenReturn(List.of(mensalidade));
		
		
		when(repository.findById(id)).thenReturn(Optional.empty());
		
		assertThrows(UserNotFoundException.class,()->service.pagarMensalidade(id, mens));
		
	}
	
	@Test
	void findAllMensalidadesAlunoSucesso() {
		Long aluno=1L;
		
		when(mensalidadeRepository.mensalidadesAluno(anyLong())).thenReturn(List.of(new Mensalidade()));
		List<MensalidadeDTO> saida = service.findAllMensalidadeAluno(aluno);
		
		assertTrue(saida.get(0)!=null);
	}
	
	@Test
	void deletarComSucesso() {
		Long id=1L;
		
		when(mensalidadeRepository.existsById(id)).thenReturn(true);
		
		String saida = service.deleteMensalidadeAluno(id);
		
	
		assertEquals(saida,"{}");
		verify(mensalidadeRepository).deleteById(id);
		
	
	}
	
	@Test
	void deletarThrowRuntimeException() {
		Long id=1L;
		
		when(mensalidadeRepository.existsById(id)).thenReturn(false);
		
		assertThrows(RuntimeException.class,()->service.deleteMensalidadeAluno(id));
		
	
		
	
	}
	
	@Test
	void deletarAlunoComSucesso() {
		Long id=1L;
		
		when(repository.existsById(id)).thenReturn(true);
		
		String saida = service.removerAluno(id);
		
	
		assertEquals(saida,"{}");
		verify(repository).deleteById(id);
		
	
	}
	
	@Test
	void deletarAlunoThrowUserNotFoundException() {
		Long id=1L;
		
		when(repository.existsById(id)).thenReturn(false);
		
		assertThrows(UserNotFoundException.class,()->service.removerAluno(id));
		
	
		
	
	}
	
	@Test
	void atualizarAlunoComSucesso() {
		
		Long id=1L;
		String saidaIdeal="ok";
		
		Aluno a=new Aluno();
		a.setId(id);
		a.setName("Carlos Henrique");
		
		when(repository.findById(anyLong())).thenReturn(Optional.of(aluno));
		
		String saida = service.atualizarAluno(a);
		
		
		assertEquals(saidaIdeal,saida);
		
		
		
	}
	
	@Test
	void atualizarAlunoThrowUserNotFoundException() {
		
		Long id=1L;
		
		Aluno a=new Aluno();
		a.setId(id);
		a.setName("Carlos Henrique");
		
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		
		assertThrows(UserNotFoundException.class,()-> service.atualizarAluno(a));
		
		
		
		
		
	}
	
	
	@Test
	void testarFindAllAlunosDevedoresComSucesso() {
		when(repository.findAllAlunosDevedores(anyInt(), any(PageRequest.class))).thenReturn(new PageImpl<>(lista));
		int mes=1;
		Page<AlunoDTO> saida = service.findAllAlunosDevedores(mes, 0, 4);
		
		assertEquals(saida.isFirst(),true);
		assertEquals(saida.isLast(),true);

		
		assertEquals(saida.getContent().size(),lista.stream().map(MapperAluno::toDTO).collect(Collectors.toList()).size());
	}
}
