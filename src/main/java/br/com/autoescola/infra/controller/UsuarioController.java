package br.com.autoescola.infra.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.autoescola.domain.dto.usuario.AlterarSenhaDTO;
import br.com.autoescola.domain.dto.usuario.DadosDetalhamentoUsuarioDTO;
import br.com.autoescola.domain.dto.usuario.ListagemUsuarioDTO;
import br.com.autoescola.domain.dto.usuario.UsuarioCreateDTO;
import br.com.autoescola.domain.dto.usuario.UsuarioUpdateDTO;
import br.com.autoescola.domain.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DadosDetalhamentoUsuarioDTO> cadastrar(
            @RequestBody @Valid UsuarioCreateDTO dados,
            UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoUsuarioDTO usuario = service.cadastrar(dados);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.id()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<ListagemUsuarioDTO>> listar(
            @PageableDefault(size = 10, sort = {"login"}) Pageable paginacao) {
        Page<ListagemUsuarioDTO> page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DadosDetalhamentoUsuarioDTO> detalhar(@PathVariable Long id) {
        DadosDetalhamentoUsuarioDTO usuario = service.detalhar(id);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DadosDetalhamentoUsuarioDTO> atualizar(@RequestBody @Valid UsuarioUpdateDTO dados) {
        DadosDetalhamentoUsuarioDTO usuario = service.atualizar(dados);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/alterar-senha")
    @Transactional
    public ResponseEntity<Void> alterarSenha(@RequestBody @Valid AlterarSenhaDTO dados) {
        service.alterarSenha(dados);
        return ResponseEntity.ok().build();
    }
}
