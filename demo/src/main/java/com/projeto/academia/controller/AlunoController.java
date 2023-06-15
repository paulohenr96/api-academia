package com.projeto.academia.controller;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.academia.dto.MensalidadeDTO;
import com.projeto.academia.dto.PaginacaoDTO;
import com.projeto.academia.dto.UsuarioDTO;
import com.projeto.academia.model.Aluno;
import com.projeto.academia.model.Mensalidade;
import com.projeto.academia.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
	
	
private final AlunoService alunoService;
	
	public AlunoController(AlunoService alunoService) {
		// TODO Auto-generated constructor stub
		
		this.alunoService=alunoService;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteAluno(@PathVariable Long id){
		
		return new ResponseEntity<Object>(alunoService.removerAluno(id),HttpStatus.NO_CONTENT);
	}
	@GetMapping
	public ResponseEntity<Page<Aluno>> getAllAlunos(@RequestParam("page") int page,@RequestParam("size") int size){
		
		return new ResponseEntity<>(alunoService.findAllAluno(page,size),HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> getAluno(@PathVariable Long id){
		
		return new ResponseEntity<Aluno>(alunoService.findAlunoById(id),HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<String> salvarAluno(@RequestBody Aluno aluno){
		
		alunoService.salvarAluno(aluno);
		return new ResponseEntity<String>("ok",HttpStatus.CREATED);
	}
	
	@PostMapping("/{id}/mensalidade")
	public ResponseEntity<String> pagarMensalidadeAluno(@PathVariable Long id,@Valid @RequestBody MensalidadeDTO mensalidade){
		
		alunoService.pagarMensalidade(id,mensalidade);
		return new ResponseEntity<String>("ok",HttpStatus.OK);

	}
	
	@GetMapping("/{id}/mensalidade")
	public ResponseEntity<List<MensalidadeDTO>> todasMensalidadesAluno(@PathVariable Long id){
		
		return new ResponseEntity<List<MensalidadeDTO>>(alunoService.findAllMensalidadeAluno(id),HttpStatus.OK);

	}
	
	@DeleteMapping("/mensalidade/{idMensalidade}")
	public ResponseEntity<Object> deleteMensalidadeAluno(@PathVariable Long idMensalidade){
		
		return new ResponseEntity<>(alunoService.deleteMensalidadeAluno(idMensalidade),HttpStatus.NO_CONTENT);

	}
	
	@PutMapping
	public ResponseEntity<Object> atualizarAluno(@RequestBody Aluno alunoNovo){
		return new ResponseEntity<>(alunoService.atualizarAluno(alunoNovo),HttpStatus.OK);

	}
	
	@GetMapping("/devedores/{mes}")
	public ResponseEntity<PaginacaoDTO<Aluno>> getAllAlunosDevedores(
			@PathVariable int mes
			,@RequestParam("page") int page
			,@RequestParam("size") int size){
		
		return new ResponseEntity<PaginacaoDTO<Aluno>>(alunoService.findAllAlunosDevedores( mes,page,size),HttpStatus.OK);
	}
}
