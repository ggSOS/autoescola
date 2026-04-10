package br.com.autoescola.adapter.in.controller.response.aluno;

import br.com.autoescola.application.core.domain.model.Aluno;

public record AlunoResponseDTO(
        Long id,

        String nome,

        String email,

        String cpf,
        
        Boolean ativo) {

    public AlunoResponseDTO(Aluno aluno) {
        this(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getCpf(),
                aluno.getAtivo());
    }
}
