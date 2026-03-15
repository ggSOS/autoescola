package br.com.autoescola.infra.controller;

import java.net.URI;
import java.util.Optional;

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

import br.com.autoescola.domain.dto.instrutor.DadosDetalhamentoInstrutorDTO;
import br.com.autoescola.domain.dto.instrutor.InstrutorCreateDTO;
import br.com.autoescola.domain.dto.instrutor.InstrutorUpdateDTO;
import br.com.autoescola.domain.dto.instrutor.ListagemInstrutorDTO;
import br.com.autoescola.domain.model.Instrutor;
import br.com.autoescola.domain.repository.InstrutorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/instrutores")
public class InstrutorController {

    private final InstrutorRepository repository;

    @PostMapping
    @Transactional
    // @PreAuthorize("hasAnyRole('ADMIN', 'USER')") se autorizacao pelo Controller
    // estiver habilitada
    public ResponseEntity<DadosDetalhamentoInstrutorDTO> cadastrarInstrutor(
            @RequestBody @Valid InstrutorCreateDTO dados,
            UriComponentsBuilder uriBuilder) {
        Instrutor instrutor = new Instrutor(dados);
        repository.save(instrutor);
        URI uri = uriBuilder.path("/instrutores/{id}").buildAndExpand(instrutor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoInstrutorDTO(instrutor));
    }

    @GetMapping
    // Page é uma lista de lista(para criar funcionalidade de páginas)
    // @PageableDefault seta o padrão(sem adicionar modificadores no enpoint) da
    // busca do frontend
    public ResponseEntity<Page<ListagemInstrutorDTO>> listarInstrutores(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        Page<ListagemInstrutorDTO> page = repository.findAllByAtivoTrue(paginacao).map(ListagemInstrutorDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoInstrutorDTO> detalharInstrutor(@PathVariable Long id) {
        return repository.findById(id)
                .map(instrutor -> ResponseEntity
                        .ok(new DadosDetalhamentoInstrutorDTO(instrutor)))
                .orElseGet(() -> ResponseEntity
                        .notFound().build());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoInstrutorDTO> atualizarInstrutores(
            @RequestBody @Valid InstrutorUpdateDTO dados) {

        Instrutor instrutor = repository.getReferenceById(dados.id());
        instrutor.atualizarInformacoes(dados);
        // Criar outro DTO para filtrar dados de saida caso necessario
        return ResponseEntity.ok(new DadosDetalhamentoInstrutorDTO(instrutor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoInstrutorDTO> deletarInstrutor(@PathVariable Long id) {
        Optional<Instrutor> optionalInstrutor = repository.findById(id);

        if (optionalInstrutor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Instrutor instrutor = optionalInstrutor.get();
        instrutor.excluir();
        // Padrao de mercado
        // "public ResponseEntity<Void> deletarInstrutores(){}"
        // "return ResponseEntity.noContent().build();"
        //
        // Versao mais completa/util
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new DadosDetalhamentoInstrutorDTO(instrutor));
    }
}
