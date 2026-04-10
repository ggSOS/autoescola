package br.com.autoescola.adapter.in.controller.response.instrutor;

import br.com.autoescola.application.core.domain.enums.Especialidade;
import br.com.autoescola.application.core.domain.model.Instrutor;
import br.com.autoescola.application.core.domain.vo.EnderecoVO;

public record DadosDetalhamentoInstrutorDTO(
        Long id,
        Boolean ativo,
        String nome,
        String email,
        String telefone,
        String cnh,
        Especialidade especialidade,
        EnderecoVO endereco) {
    public DadosDetalhamentoInstrutorDTO(Instrutor instrutor) {
        this(
                instrutor.getId(),
                instrutor.getAtivo(),
                instrutor.getNome(),
                instrutor.getEmail(),
                instrutor.getTelefone(),
                instrutor.getCnh(),
                instrutor.getEspecialidade(),
                instrutor.getEndereco());
    }
}
