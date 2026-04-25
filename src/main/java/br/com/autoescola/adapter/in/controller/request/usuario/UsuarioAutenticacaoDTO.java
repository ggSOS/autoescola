package br.com.autoescola.adapter.in.controller.request.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioAutenticacaoDTO(
        @NotBlank String login,
        @NotBlank String senha) {
}
