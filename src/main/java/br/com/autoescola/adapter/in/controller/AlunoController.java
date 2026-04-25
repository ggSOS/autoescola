package br.com.autoescola.adapter.in.controller;

import java.net.URI;

import br.com.autoescola.application.core.usecase.AlunoServicePort;
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
import br.com.autoescola.adapter.in.controller.response.aluno.AlunoResponseDTO;
import br.com.autoescola.adapter.in.controller.request.aluno.AlunoUpdateDTO;
import br.com.autoescola.application.core.domain.model.Aluno;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoServicePort service;

    @GetMapping
    public ResponseEntity<Page<AlunoResponseDTO>> listarAlunos(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        Page<AlunoResponseDTO> page = service
                .listarAlunos(paginacao)
                .map(AlunoResponseDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> detalharAluno(@PathVariable Long id) {
        Aluno aluno = service.detalharAluno(id);
        return ResponseEntity.ok(new AlunoResponseDTO(aluno));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AlunoResponseDTO> cadastrarAluno(
            @RequestBody @Valid AlunoCreateDTO dados,
            UriComponentsBuilder uriBuilder) {
        Aluno aluno = service.cadastrarAluno(dados);
        URI uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new AlunoResponseDTO(aluno));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AlunoResponseDTO> atualizarAluno(
            @PathVariable Long id,
            @RequestBody @Valid AlunoUpdateDTO dados) {
        Aluno aluno = service.atualizarAluno(id, dados);
        return ResponseEntity.ok(new AlunoResponseDTO(aluno));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<AlunoResponseDTO> deletarAlunos(@PathVariable Long id) {
        service.deletarAluno(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
