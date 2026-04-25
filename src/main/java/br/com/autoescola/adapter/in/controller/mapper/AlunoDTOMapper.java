package br.com.autoescola.adapter.in.controller.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.autoescola.adapter.in.controller.request.aluno.AlunoCreateDTO;
import br.com.autoescola.adapter.in.controller.response.aluno.AlunoDetailedResponseDTO;
import br.com.autoescola.adapter.in.controller.response.aluno.AlunoResponseDTO;
import br.com.autoescola.application.core.domain.model.Aluno;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Service
public class AlunoDTOMapper {
    private final EnderecoDTOMapper enderecoMapper;

    public Aluno toDomain(AlunoCreateDTO dados) {
        return new Aluno(
                null,
                dados.nome(),
                dados.email(),
                dados.telefone(),
                dados.cpf(),
                enderecoMapper.toDomain(dados.endereco()),
                true);
    }

    public AlunoResponseDTO toResponseDTO(Aluno dados) {
        return new AlunoResponseDTO(
                dados.getId(),
                dados.getNome(),
                dados.getEmail(),
                dados.getCpf(),
                dados.getAtivo());
    }

    public AlunoDetailedResponseDTO toDetailedResponseDTO(Aluno dados) {
        return new AlunoDetailedResponseDTO(
                dados.getId(),
                dados.getNome(),
                dados.getEmail(),
                dados.getTelefone(),
                dados.getCpf(),
                dados.getAtivo(),
                enderecoMapper.toDTO(dados.getEndereco()));
    }
}
