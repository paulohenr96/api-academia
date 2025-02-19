package com.projeto.academia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projeto.academia.dto.AlunoDTO;
import com.projeto.academia.dto.MensalidadeDTO;
import com.projeto.academia.mapper.MapperAluno;
import com.projeto.academia.model.Aluno;
import com.projeto.academia.service.AlunoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("alunos")
@Tag(name = "Alunos", description = "CRUD dos alunos da academia")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    /**
     * GET endpoints (Read operations)
     */
    @GetMapping
    @Operation(summary = "Todos os alunos", description = "Lista paginada de alunos.")
    public ResponseEntity<Page<Aluno>> getAllAlunos(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(alunoService.findAllAluno(page, size));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar aluno", description = "Obtém informações de um aluno pelo ID.")
    public ResponseEntity<AlunoDTO> getAluno(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.findAlunoById(id));
    }

    @GetMapping("/{id}/mensalidade")
    @Operation(summary = "Consultar mensalidades", description = "Lista todas as mensalidades pagas por um aluno.")
    public ResponseEntity<List<MensalidadeDTO>> todasMensalidadesAluno(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.findAllMensalidadeAluno(id));
    }

    @GetMapping("/devedores/{mes}")
    @Operation(summary = "Consultar alunos devedores", description = "Lista alunos que não pagaram a mensalidade em um mês específico.")
    public ResponseEntity<Page<AlunoDTO>> getAllAlunosDevedores(@PathVariable int mes, @RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(alunoService.findAllAlunosDevedores(mes, page, size));
    }

    /**
     * POST endpoints (Create operations)
     */
    @PostMapping
    @Operation(summary = "Cadastrar novo aluno", description = "Cria um novo aluno.")
    public ResponseEntity<String> salvarAluno(@RequestBody AlunoDTO alunoDTO) {
        alunoService.salvarAluno(MapperAluno.toEntity(alunoDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body("Aluno cadastrado com sucesso!");
    }

    @PostMapping("/{id}/mensalidade")
    @Operation(summary = "Pagar mensalidade do aluno", description = "Registra o pagamento da mensalidade do aluno.")
    public ResponseEntity<String> pagarMensalidadeAluno(@PathVariable Long id, @Valid @RequestBody MensalidadeDTO mensalidade) {
        alunoService.pagarMensalidade(id, mensalidade);
        return ResponseEntity.ok("Mensalidade paga com sucesso!");
    }

    /**
     * PUT endpoints (Update operations)
     */
    @PutMapping
    @Operation(summary = "Atualizar aluno", description = "Atualiza os dados de um aluno existente.")
    public ResponseEntity<Object> atualizarAluno(@RequestBody Aluno alunoNovo) {
        return ResponseEntity.ok(alunoService.atualizarAluno(alunoNovo));
    }

    /**
     * DELETE endpoints (Delete operations)
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Remover aluno", description = "Remove um aluno pelo ID.")
    public ResponseEntity<Object> deleteAluno(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.removerAluno(id));
    }

    @DeleteMapping("/mensalidade/{idMensalidade}")
    @Operation(summary = "Remover mensalidade", description = "Remove uma mensalidade pelo ID.")
    public ResponseEntity<Object> deleteMensalidadeAluno(@PathVariable Long idMensalidade) {
        return ResponseEntity.ok(alunoService.deleteMensalidadeAluno(idMensalidade));
    }
}
