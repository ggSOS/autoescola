package br.com.autoescola.adapter.out.repository.mapper;

import br.com.autoescola.adapter.out.repository.entity.InstrutorEntity;
import br.com.autoescola.application.core.domain.model.Instrutor;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;

@Component
public class InstrutorEntityMapper {
    public Instrutor toDomain(InstrutorEntity entity){
        return new Instrutor(
                entity.getId(),
                entity.getAtivo(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getCnh(),
                entity.getEspecialidade(),
                entity.getEndereco()
        );
    }

    public InstrutorEntity toEntity(Instrutor instrutor){
        return new InstrutorEntity(
                instrutor.getId(),
                instrutor.getAtivo(),
                instrutor.getNome(),
                instrutor.getEmail(),
                instrutor.getTelefone(),
                instrutor.getCnh(),
                instrutor.getEspecialidade(),
                instrutor.getEndereco()
        );
    }
}
