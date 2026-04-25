package br.com.autoescola.adapter.in.controller.request.instrutor;

import br.com.autoescola.adapter.in.controller.request.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record InstrutorUpdateDTO(
        @NotNull Long id,
        String nome,
        String telefone,
        EnderecoDTO endereco) {
}
