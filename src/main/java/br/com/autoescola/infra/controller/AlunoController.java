package br.com.autoescola.infra.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.net.URI;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.autoescola.domain.dto.aluno.AlunoCreateDTO;
import br.com.autoescola.domain.dto.aluno.AlunoResponseDTO;
import br.com.autoescola.domain.model.Aluno;
import br.com.autoescola.domain.repository.AlunoRepository;
import jakarta.validation.Valid;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<AlunoResponseDTO> cadastrarAluno(
            @RequestBody @Valid AlunoCreateDTO dados,
            UriComponentsBuilder uriBuilder) {
        Aluno aluno = new Aluno(dados);
        repository.save(aluno);
        URI uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new AlunoResponseDTO(aluno));
    }

    @GetMapping
    public ResponseEntity<Page<AlunoResponseDTO>> listarAlunos(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        Page page = repository.findAllByAtivoTrue(paginacao).map(AlunoResponseDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> detalharAluno(@PathVariable Long id){
        Aluno aluno = repository.getReferenceById(id);
        return ResponseEntity.ok(new AlunoResponseDTO(aluno));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<AlunoResponseDTO> deletarAlunos(@PathVariable Long id) {
        Aluno aluno = repository.getReferenceById(id);
        aluno.excluir();
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new AlunoResponseDTO(aluno));
    }
}
