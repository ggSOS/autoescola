package br.com.autoescola.adapter.out.repository.mapper;

import br.com.autoescola.adapter.out.repository.entity.InstrutorEntity;
import br.com.autoescola.application.core.domain.model.Instrutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class InstrutorEntityMapper {
    public Instrutor toDomain(InstrutorEntity dados) {
        return new Instrutor(
                dados.getId(),
                dados.getAtivo(),
                dados.getNome(),
                dados.getEmail(),
                dados.getTelefone(),
                dados.getCnh(),
                dados.getEspecialidade(),
                dados.getEndereco());
    }

    public InstrutorEntity toEntity(Instrutor dados) {
        return new InstrutorEntity(
                dados.getId(),
                dados.getAtivo(),
                dados.getNome(),
                dados.getEmail(),
                dados.getTelefone(),
                dados.getCnh(),
                dados.getEspecialidade(),
                dados.getEndereco());
    }
}
