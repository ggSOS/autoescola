package br.com.autoescola.adapter.in.controller.response.instrutor;

import br.com.autoescola.application.core.domain.enums.Especialidade;

public record InstrutorResponseDTO(
        Long id,
        String nome,
        String email,
        Especialidade especialidade) {
}
