package br.com.autoescola.adapter.in.controller.request.aluno;

import br.com.autoescola.adapter.in.controller.request.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record AlunoUpdateDTO(
        @NotNull Long id,

        String nome,

        String telefone,

        EnderecoDTO endereco) {

}
