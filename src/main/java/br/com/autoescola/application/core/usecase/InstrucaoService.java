package br.com.autoescola.application.core.usecase;

import java.util.List;

import br.com.autoescola.exception.type.instrucao.InstrucaoNotFoundException;
import org.springframework.stereotype.Service;

import br.com.autoescola.adapter.in.controller.request.instrucao.InstrucaoCreateDTO;
import br.com.autoescola.adapter.in.controller.request.instrucao.InstrucaoDeleteDTO;
import br.com.autoescola.adapter.in.controller.response.instrucao.InstrucaoResponseDTO;
import br.com.autoescola.adapter.out.repository.entity.AlunoEntity;
import br.com.autoescola.adapter.out.repository.entity.InstrutorEntity;
import br.com.autoescola.adapter.out.repository.mapper.AlunoEntityMapper;
import br.com.autoescola.adapter.out.repository.mapper.InstrucaoEntityMapper;
import br.com.autoescola.adapter.out.repository.mapper.InstrutorEntityMapper;
import br.com.autoescola.adapter.out.repository.persistance.AlunoRepository;
import br.com.autoescola.adapter.out.repository.persistance.InstrucaoRepository;
import br.com.autoescola.adapter.out.repository.persistance.InstrutorRepository;
import br.com.autoescola.application.core.domain.model.Aluno;
import br.com.autoescola.application.core.domain.model.Instrucao;
import br.com.autoescola.application.core.domain.model.Instrutor;
import br.com.autoescola.application.core.validation.interfaces.ValidadorAgendamento;
import br.com.autoescola.application.core.validation.interfaces.ValidadorCancelamento;
import br.com.autoescola.exception.type.aluno.AlunoNotFoundException;
import br.com.autoescola.exception.type.instrucao.InvalidDateException;
import br.com.autoescola.exception.type.instrutor.EspecialidadeException;
import br.com.autoescola.exception.type.instrutor.InstrutorNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstrucaoService {

    private final AlunoRepository alunoRepository;
    private final InstrutorRepository instrutorRepository;
    private final InstrucaoRepository instrucaoRepository;

    private final List<ValidadorAgendamento> validadoresAgendamento;
    private final List<ValidadorCancelamento> validadorCancelamentos;

    private final AlunoEntityMapper alunoEntityMapper;
    private final InstrutorEntityMapper instrutorEntityMapper;
    private final InstrucaoEntityMapper instrucaoEntityMapper;

    private InstrutorEntity escolherInstrutor(InstrucaoCreateDTO dados) {
        if (dados.idInstrutor() != null) {
            return instrutorRepository.getReferenceById(dados.idInstrutor());
        }

        if (dados.especialidade() == null) {
            throw new EspecialidadeException("Especialidade é obrigatória quando Instrutor não for escolhido.");
        }

        return instrutorRepository.escolherInstrutorAleatorioDisponivel(dados.especialidade(), dados.data());
    }

    @Transactional
    public InstrucaoResponseDTO agendar(InstrucaoCreateDTO dados) {
        if (!alunoRepository.existsById(dados.idAluno())) {
            throw new AlunoNotFoundException("Id do aluno informado não existe.");
        }

        if (dados.idInstrutor() != null && !instrutorRepository.existsById(dados.idInstrutor())) {
            throw new InstrutorNotFoundException("Id do instrutor informado não existe.");
        }

        validadoresAgendamento
                .forEach(v -> v
                        .validar(dados));

        Aluno aluno = alunoEntityMapper.toDomain(alunoRepository.getReferenceById(dados.idAluno()));
        Instrutor instrutor = instrutorEntityMapper.toDomain(escolherInstrutor(dados));
        if (instrutor == null) {
            throw new InvalidDateException(
                    "Não existe instrutor disponível para adata / hora informada!");
        }

        Instrucao instrucao = new Instrucao(
                null,
                aluno,
                instrutor,
                dados.data(),
                null);
        instrucaoRepository.save(instrucaoEntityMapper.toEntity(instrucao));
        return new InstrucaoResponseDTO(
                instrucao.getId(),
                instrucao.getAluno().getNome(),
                instrucao.getInstrutor().getNome(),
                instrucao.getData());
    }


    @Transactional
    public void cancelar(InstrucaoDeleteDTO dados) {
        if (!instrucaoRepository.existsById(dados.id())) {
            throw new InstrucaoNotFoundException("Id da instrução informado não existe.");
        }

        validadorCancelamentos.forEach(v -> v.validar(dados));

        var entity = instrucaoRepository.getReferenceById(dados.id());
        var instrucao = instrucaoEntityMapper.toDomain(entity);
        instrucao.cancelar(dados.motivoCancelamento());
        instrucaoRepository.save(instrucaoEntityMapper.toEntity(instrucao));
    }

}
