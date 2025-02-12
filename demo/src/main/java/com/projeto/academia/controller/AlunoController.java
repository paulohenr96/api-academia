package com.projeto.academia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.academia.dto.AlunoDTO;
import com.projeto.academia.dto.MensalidadeDTO;
import com.projeto.academia.dto.PaginacaoDTO;
import com.projeto.academia.mapper.MapperAluno;
import com.projeto.academia.model.Aluno;
import com.projeto.academia.service.AlunoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("alunos")
@Api(tags="CRUD dos alunos da academia")
public class AlunoController {
	
	
private final AlunoService alunoService;
	
	public AlunoController(AlunoService alunoService) {
		// TODO Auto-generated constructor stub
		
		this.alunoService=alunoService;
	}
	
	@DeleteMapping("{id}")
	@ApiOperation("Remover aluno")
	public ResponseEntity<Object> deleteAluno(@PathVariable Long id){
		
		return new ResponseEntity<Object>(alunoService.removerAluno(id),HttpStatus.OK);
	}
	
	
	@GetMapping
	@ApiOperation("Todos os alunos")
	public ResponseEntity<Page<Aluno>> getAllAlunos(@RequestParam("page") int page,@RequestParam("size") int size){
		
		return new ResponseEntity<>(alunoService.findAllAluno(page,size),HttpStatus.OK);
	}
	@GetMapping("/{id}")
	@ApiOperation("Consultar aluno")
	public ResponseEntity<AlunoDTO> getAluno(@PathVariable Long id){
		
		return new ResponseEntity<AlunoDTO>(alunoService.findAlunoById(id),HttpStatus.FOUND);
	}
	
	@PostMapping
	@ApiOperation("Cadastrar novo aluno")
	public ResponseEntity<String> salvarAluno(@RequestBody AlunoDTO alunoDTO){
		
		alunoService.salvarAluno(MapperAluno.toEntity(alunoDTO));
		return new ResponseEntity<String>("ok",HttpStatus.CREATED);
	}
	
	
	
	@PostMapping("/{id}/mensalidade")
	@ApiOperation("Pagar mensalidade do aluno")
	public ResponseEntity<String> pagarMensalidadeAluno
			(@ApiParam("ID do aluno") @PathVariable Long id,
			@ApiParam("DTO contendo as informações da mensalidade") @Valid @RequestBody MensalidadeDTO mensalidade){
		
		alunoService.pagarMensalidade(id,mensalidade);
		return new ResponseEntity<String>("ok",HttpStatus.OK);

	}
	
	@GetMapping("/{id}/mensalidade")
	@ApiOperation("Consultar todas as mensalidades do aluno.")
	public ResponseEntity<List<MensalidadeDTO>> todasMensalidadesAluno(@PathVariable Long id){
		
		return new ResponseEntity<List<MensalidadeDTO>>(alunoService.findAllMensalidadeAluno(id),HttpStatus.OK);

	}
	
	@DeleteMapping("/mensalidade/{idMensalidade}")
	@ApiOperation("Remover todas as mensalidades do aluno")
	public ResponseEntity<Object> deleteMensalidadeAluno(@PathVariable Long idMensalidade){
		
		return new ResponseEntity<>(alunoService.deleteMensalidadeAluno(idMensalidade),HttpStatus.OK);

	}
	
	@PutMapping
	@ApiOperation("Atualizar o aluno")
	public ResponseEntity<Object> atualizarAluno(@RequestBody Aluno alunoNovo){
		return new ResponseEntity<>(alunoService.atualizarAluno(alunoNovo),HttpStatus.OK);

	}
	
	@GetMapping("/devedores/{mes}")
	@ApiOperation("Consultar todos os alunos que não pagaram determinado mês")
	public ResponseEntity<Page<AlunoDTO>> getAllAlunosDevedores(
			@PathVariable int mes
			,@RequestParam("page") int page
			,@RequestParam("size") int size){
		
		return new ResponseEntity<Page<AlunoDTO>>(alunoService.findAllAlunosDevedores( mes,page,size),HttpStatus.OK);
	}
}
