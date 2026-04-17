package br.com.autoescola.adapter.in.controller.mapper;

import br.com.autoescola.adapter.in.controller.request.instrutor.InstrutorCreateDTO;
import br.com.autoescola.adapter.in.controller.response.instrutor.DadosDetalhamentoInstrutorDTO;

import br.com.autoescola.application.core.domain.model.Instrutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InstrutorMapper {
    private final EnderecoMapper enderecoMapper;

    public Instrutor toDomain(InstrutorCreateDTO dados) {
        return new Instrutor(
                null,
                true,
                dados.nome(),
                dados.email(),
                dados.cnh(),
                dados.telefone(),
                dados.especialidade(),
                enderecoMapper.toDomain(dados.endereco())
        );
    }

    public DadosDetalhamentoInstrutorDTO toResponseDTO(Instrutor instrutor) {
        return new DadosDetalhamentoInstrutorDTO(
                instrutor.getId(),
                instrutor.getAtivo(),
                instrutor.getNome(),
                instrutor.getEmail(),
                instrutor.getCnh(),
                instrutor.getTelefone(),
                instrutor.getEspecialidade(),
                instrutor.getEndereco()
        );
    }
}
