package br.com.autoescola.adapter.out.repository.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.autoescola.adapter.out.repository.entity.AlunoEntity;
import br.com.autoescola.application.core.domain.model.Aluno;

@Component
@Service
public class AlunoEntityMapper {

    public Aluno toDomain(AlunoEntity dados) {
        return new Aluno(
                dados.getId(),
                dados.getNome(),
                dados.getEmail(),
                dados.getTelefone(),
                dados.getCpf(),
                dados.getEndereco(),
                dados.getAtivo());
    }

    public AlunoEntity toEntity(Aluno dados) {
        return new AlunoEntity(
                dados.getId(),
                dados.getNome(),
                dados.getEmail(),
                dados.getTelefone(),
                dados.getCpf(),
                dados.getEndereco(),
                dados.getAtivo());
    }
}
