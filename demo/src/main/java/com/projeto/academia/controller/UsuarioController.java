package com.projeto.academia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.academia.dto.UsuarioDTO;
import com.projeto.academia.model.Usuario;
import com.projeto.academia.service.UsuarioServiceImpl;



@RestController
@RequestMapping("usuario/")
public class UsuarioController {

	private final UsuarioServiceImpl usuarioService;
	
	public UsuarioController(UsuarioServiceImpl usuarioService) {
		// TODO Auto-generated constructor stub
		
		this.usuarioService=usuarioService;
	}
	
	
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<String> salvar(@Valid @RequestBody Usuario usuario){		
		usuarioService.salvar(usuario);
		return new ResponseEntity("ok",HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> getAll(){		
		return new ResponseEntity(usuarioService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id){		
		return new ResponseEntity(usuarioService.findById(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		usuarioService.delete(id);
		return new ResponseEntity("{}",HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable Long id,@RequestBody Usuario usuarioNovo){		
		usuarioService.update(usuarioNovo,id);
		return new ResponseEntity("ok",HttpStatus.OK);
	}
	
	
	
}
