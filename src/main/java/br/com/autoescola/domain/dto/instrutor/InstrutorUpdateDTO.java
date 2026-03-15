package br.com.autoescola.domain.dto.instrutor;

import br.com.autoescola.domain.dto.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record InstrutorUpdateDTO(
        @NotNull
        Long id,

        String nome,
        String telefone,
        EnderecoDTO endereco) {
}
