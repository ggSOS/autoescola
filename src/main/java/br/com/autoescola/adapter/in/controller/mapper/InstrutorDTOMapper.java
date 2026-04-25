package br.com.autoescola.adapter.in.controller.mapper;

import br.com.autoescola.adapter.in.controller.request.instrutor.InstrutorCreateDTO;
import br.com.autoescola.adapter.in.controller.response.instrutor.InstrutorDetailedResponseDTO;
import br.com.autoescola.adapter.in.controller.response.instrutor.InstrutorResponseDTO;
import br.com.autoescola.application.core.domain.model.Instrutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Component
@Service
public class InstrutorDTOMapper {
    private final EnderecoDTOMapper enderecoMapper;

    public Instrutor toDomain(InstrutorCreateDTO dados) {
        return new Instrutor(
                null,
                true,
                dados.nome(),
                dados.email(),
                dados.cnh(),
                dados.telefone(),
                dados.especialidade(),
                enderecoMapper.toDomain(dados.endereco()));
    }

    public InstrutorResponseDTO toResponseDTO(Instrutor dados) {
        return new InstrutorResponseDTO(
                dados.getId(),
                dados.getNome(),
                dados.getEmail(),
                dados.getEspecialidade());
    }

    public InstrutorDetailedResponseDTO toDetailedResponseDTO(Instrutor dados) {
        return new InstrutorDetailedResponseDTO(
                dados.getId(),
                dados.getAtivo(),
                dados.getNome(),
                dados.getEmail(),
                dados.getCnh(),
                dados.getTelefone(),
                dados.getEspecialidade(),
                enderecoMapper.toDTO(dados.getEndereco()));
    }
}
