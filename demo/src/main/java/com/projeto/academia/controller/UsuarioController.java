package com.projeto.academia.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projeto.academia.dto.UsuarioDTO;
import com.projeto.academia.model.Usuario;
import com.projeto.academia.service.UsuarioServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("usuarios")
@Tag(name = "Usuários", description = "CRUD dos usuários do serviço (administradores e secretários)")  // ✅ Defines API section
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    public UsuarioController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(consumes = "application/json")
    @Operation(summary = "Cadastra um novo usuário", description = "Registra um novo usuário no sistema.")
    public ResponseEntity<String> salvar(@Valid @RequestBody Usuario usuario) {
        usuarioService.salvar(usuario);
        return new ResponseEntity<>("Usuário cadastrado com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Consulta todos os usuários", description = "Retorna uma lista paginada de usuários.")
    public ResponseEntity<Page<UsuarioDTO>> getAll(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "3") int size) {
        PageRequest of = PageRequest.of(page, size);
        return new ResponseEntity<>(usuarioService.findAll(of), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulta um usuário pelo ID", description = "Busca um usuário específico pelo seu ID.")
    public ResponseEntity<UsuarioDTO> getById(
            @Parameter(description = "ID do usuário") @PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um usuário", description = "Exclui um usuário do sistema pelo ID.")
    public ResponseEntity<String> delete(
            @Parameter(description = "ID do usuário a ser removido") @PathVariable Long id) {
        usuarioService.delete(id);
        return new ResponseEntity<>("Usuário removido com sucesso!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um usuário", description = "Modifica os dados de um usuário pelo ID.")
    public ResponseEntity<String> update(
            @Parameter(description = "ID do usuário a ser atualizado") @PathVariable Long id,
            @RequestBody Usuario usuarioNovo) {
        usuarioService.update(usuarioNovo, id);
        return new ResponseEntity<>("Usuário atualizado com sucesso!", HttpStatus.OK);
    }
}
