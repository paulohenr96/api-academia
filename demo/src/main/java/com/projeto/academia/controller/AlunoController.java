package com.projeto.academia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.academia.dto.UsuarioDTO;
import com.projeto.academia.model.Usuario;
import com.projeto.academia.service.AlunoServiceImpl;


@RestController
@RequestMapping("/aluno")
public class AlunoController {
	
	
private final AlunoServiceImpl alunoService;
	
	public AlunoController(AlunoServiceImpl alunoService) {
		// TODO Auto-generated constructor stub
		
		this.alunoService=alunoService;
	}
	
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> getAllAlunos(){
		
		return new ResponseEntity(alunoService.findAll(),HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> getAllAlunos(@PathVariable Long id){
		
		return new ResponseEntity(alunoService.findById(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> salvar(@Valid @RequestBody Usuario aluno){
		
		alunoService.salvar(aluno);
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
}
