package br.com.autoescola.adapter.in.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.autoescola.adapter.in.controller.request.aluno.AlunoCreateDTO;
import br.com.autoescola.adapter.in.controller.request.aluno.AlunoUpdateDTO;
import br.com.autoescola.adapter.in.controller.response.aluno.AlunoDetailedResponseDTO;
import br.com.autoescola.adapter.in.controller.response.aluno.AlunoResponseDTO;
import br.com.autoescola.application.core.usecase.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    @PostMapping
    public ResponseEntity<AlunoDetailedResponseDTO> cadastrarAluno(
            @RequestBody @Valid AlunoCreateDTO dados,
            UriComponentsBuilder uriBuilder) {
        AlunoDetailedResponseDTO dto = service.cadastrarAluno(dados);
        URI uri = uriBuilder
                .path("/alunos/{id}")
                .buildAndExpand(dto.id())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<AlunoResponseDTO>> listarAlunos(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        return ResponseEntity.ok(service.listarAlunos(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDetailedResponseDTO> detalharAluno(@PathVariable Long id) {
        return ResponseEntity.ok(service.detalharAluno(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDetailedResponseDTO> atualizarAluno(
            @PathVariable Long id,
            @RequestBody @Valid AlunoUpdateDTO dados) {
        return ResponseEntity.ok(service.atualizarAluno(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAlunos(@PathVariable Long id) {
        service.deletarAluno(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
