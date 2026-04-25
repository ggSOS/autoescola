package br.com.autoescola.adapter.in.controller.response.aluno;

public record AlunoResponseDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        Boolean ativo) {
}
