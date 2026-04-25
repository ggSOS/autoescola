package br.com.autoescola.adapter.in.controller;

import br.com.autoescola.adapter.in.controller.request.instrutor.InstrutorCreateDTO;
import br.com.autoescola.adapter.in.controller.request.instrutor.InstrutorUpdateDTO;
import br.com.autoescola.adapter.in.controller.response.instrutor.DadosDetalhamentoInstrutorDTO;
import br.com.autoescola.adapter.in.controller.response.instrutor.ListagemInstrutorDTO;
import br.com.autoescola.application.core.usecase.InstrutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<DadosDetalhamentoInstrutorDTO> cadastrarInstrutor(
            @RequestBody @Valid InstrutorCreateDTO dados,
            UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoInstrutorDTO dto = service.cadastrarInstrutor(dados);
        URI uri = uriBuilder
                .path("/instrutores/{id}")
                .buildAndExpand(dto.id())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemInstrutorDTO>> listarInstrutores(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(service.listarInstrutores(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoInstrutorDTO> detalharInstrutor(@PathVariable Long id) {
        return ResponseEntity.ok(service.detalharInstrutor(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoInstrutorDTO> atualizarInstrutor(
            @PathVariable Long id,
            @RequestBody @Valid InstrutorUpdateDTO dados) {
        return ResponseEntity.ok(service.atualizarInstrutor(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarInstrutor(@PathVariable Long id) {
        service.deletarInstrutor(id);
        return ResponseEntity.noContent().build();
    }
}
