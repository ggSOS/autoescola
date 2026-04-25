package br.com.autoescola.application.core.usecase;

import br.com.autoescola.adapter.in.controller.request.instrucao.InstrucaoCreateDTO;
import br.com.autoescola.adapter.in.controller.response.instrucao.InstrucaoResponseDTO;
import br.com.autoescola.adapter.out.repository.entity.AlunoEntity;
import br.com.autoescola.adapter.out.repository.entity.InstrutorEntity;
import br.com.autoescola.adapter.out.repository.mapper.AlunoEntityMapper;
import br.com.autoescola.adapter.out.repository.mapper.InstrutorEntityMapper;
import br.com.autoescola.application.core.domain.model.Aluno;
import br.com.autoescola.application.core.domain.model.Instrucao;
import br.com.autoescola.application.core.domain.model.Instrutor;
import br.com.autoescola.adapter.out.repository.persistance.AlunoRepository;
import br.com.autoescola.adapter.out.repository.persistance.InstrucaoRepository;
import br.com.autoescola.adapter.out.repository.persistance.InstrutorRepository;
import br.com.autoescola.application.core.validation.interfaces.ValidadorAgendamento;
import br.com.autoescola.exception.type.aluno.AlunoNotFoundException;
import br.com.autoescola.exception.type.instrutor.EspecialidadeException;
import br.com.autoescola.exception.type.instrutor.InstrutorNotFoundException;
import br.com.autoescola.exception.type.instrucao.InvalidDateException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstrucaoService{

    private final AlunoRepository alunoRepository;
    private final InstrutorRepository instrutorRepository;
    private final InstrucaoRepository instrucaoRepository;
    private final List<ValidadorAgendamento> validadoresAgendamento;
    private final AlunoEntityMapper alunoMapper;
    private final InstrutorEntityMapper instrutorMapper;

    @Transactional
    public InstrucaoResponseDTO agendar(InstrucaoCreateDTO dados) {
        if(!alunoRepository.existsById(dados.idAluno())){
            throw new AlunoNotFoundException("Id do aluno informado não existe.");
        }

        if(dados.idInstrutor()!=null && !instrutorRepository.existsById(dados.idInstrutor())){
            throw new InstrutorNotFoundException("Id do instrutor informado não existe.");
        }

        validadoresAgendamento
                .forEach(v -> v
                        .validar(dados));

        AlunoEntity alunoEntity = alunoRepository.getReferenceById(dados.idAluno());
        InstrutorEntity instrutorEntity = escolherInstrutor(dados);
        if(instrutorEntity == null){
            throw new InvalidDateException("Não existe instrutor disponível para adata / hora informada!");
        }
        
        // Aqui assumimos que Instrucao ainda usa entities diretamente ou precisa de ajuste similar
        // Por hora, ajustando apenas a busca das entities
        Instrucao instrucao = new Instrucao(null, alunoMapper.toDomain(alunoEntity), instrutorMapper.toDomain(instrutorEntity), dados.data());
        // Se instrucaoRepository esperar Entity, precisará de um mapper também.
        // instrucaoRepository.save(instrucaoMapper.toEntity(instrucao));
        
        return new InstrucaoResponseDTO(instrucao);
    }

    private InstrutorEntity escolherInstrutor(InstrucaoCreateDTO dados){
        if(dados.idInstrutor()!=null){
            return instrutorRepository.getReferenceById(dados.idInstrutor());
        }

        if(dados.especialidade()==null){
            throw new EspecialidadeException("Especialidade é obrigatória quando Instrutor não for escolhido.");
        }

        return instrutorRepository.escolherInstrutorAleatorioDisponivel(dados.especialidade(), dados.data());
    }
}
