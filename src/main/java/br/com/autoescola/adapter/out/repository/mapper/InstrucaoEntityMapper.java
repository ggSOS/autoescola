package br.com.autoescola.adapter.out.repository.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.autoescola.adapter.out.repository.entity.InstrucaoEntity;
import br.com.autoescola.application.core.domain.model.Instrucao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Service
public class InstrucaoEntityMapper {

    private final AlunoEntityMapper alunoEntityMapper;
    private final InstrutorEntityMapper instrutorEntityMapper;

    public Instrucao toDomain(InstrucaoEntity dados) {
        return new Instrucao(
                dados.getId(),
                alunoEntityMapper.toDomain(dados.getAluno()),
                instrutorEntityMapper.toDomain(dados.getInstrutor()),
                dados.getData(),
                dados.getMotivoCancelamento());
    }

    public InstrucaoEntity toEntity(Instrucao dados) {
        return new InstrucaoEntity(
                dados.getId(),
                alunoEntityMapper.toEntity(dados.getAluno()),
                instrutorEntityMapper.toEntity(dados.getInstrutor()),
                dados.getData(),
                dados.getMotivoCancelamento());
    }
}
