package br.com.autoescola.domain.dto.aluno;

import br.com.autoescola.domain.model.Aluno;

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
