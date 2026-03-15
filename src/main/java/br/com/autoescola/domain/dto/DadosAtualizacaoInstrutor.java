package br.com.autoescola.domain.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoInstrutor(
        @NotNull
        Long id,

        String nome,
        String telefone,
        EnderecoDTO endereco) {
}
