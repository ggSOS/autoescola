package br.com.autoescola.adapter.in.controller;

import br.com.autoescola.adapter.in.controller.request.instrutor.InstrutorCreateDTO;
import br.com.autoescola.adapter.in.controller.response.instrutor.DadosDetalhamentoInstrutorDTO;
import br.com.autoescola.adapter.in.controller.response.instrutor.ListagemInstrutorDTO;
import br.com.autoescola.application.core.usecase.InstrutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/instrutores")
public class InstrutorController {

    private final InstrutorService service;

    @PostMapping
    // @PreAuthorize("hasAnyRole('ADMIN', 'USER')") se autorizacao pelo Controller
    // estiver habilitada
    public ResponseEntity<DadosDetalhamentoInstrutorDTO> cadastrarInstrutor(
            @RequestBody @Valid InstrutorCreateDTO dados,
            UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoInstrutorDTO dto = service.cadastrarInstrutores(dados);
        URI uri = uriBuilder
                .path("/instrutores/{id}")
                .buildAndExpand(dto.id())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(dto);
    }

    @GetMapping
    // Page é uma lista de lista(para criar funcionalidade de páginas)
    // @PageableDefault seta o padrão(sem adicionar modificadores no enpoint) da
    // busca do frontend
    public ResponseEntity<Page<ListagemInstrutorDTO>> listarInstrutores(
        return ResponseEntity.ok(service.li);

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoInstrutorDTO> detalharInstrutor(@PathVariable Long id) {
        return service.findById(id)
                .map(instrutor -> ResponseEntity
                        .ok(new DadosDetalhamentoInstrutorDTO(instrutor)))
                .orElseGet(() -> ResponseEntity
                        .notFound().build());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoInstrutorDTO> atualizarInstrutores(
            @RequestBody @Valid InstrutorUpdateDTO dados) {

        Instrutor instrutor = service.getReferenceById(dados.id());
        instrutor.atualizarInformacoes(dados);
        // Criar outro DTO para filtrar dados de saida caso necessario
        return ResponseEntity.ok(new DadosDetalhamentoInstrutorDTO(instrutor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoInstrutorDTO> deletarInstrutor(@PathVariable Long id) {
        Optional<Instrutor> optionalInstrutor = service.findById(id);

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
