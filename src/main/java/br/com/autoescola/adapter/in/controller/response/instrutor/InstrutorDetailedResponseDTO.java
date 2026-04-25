package br.com.autoescola.adapter.in.controller.response.instrutor;

import br.com.autoescola.adapter.in.controller.request.endereco.EnderecoDTO;
import br.com.autoescola.application.core.domain.enums.Especialidade;

public record InstrutorDetailedResponseDTO(
        Long id,
        Boolean ativo,
        String nome,
        String email,
        String telefone,
        String cnh,
        Especialidade especialidade,
        EnderecoDTO endereco) {
}
