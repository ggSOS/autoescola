package br.com.autoescola.adapter.in.controller.response.aluno;

import br.com.autoescola.adapter.in.controller.request.endereco.EnderecoDTO;

public record AlunoDetailedResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Boolean ativo,
        EnderecoDTO endereco) {
}
