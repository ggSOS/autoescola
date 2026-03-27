package br.com.autoescola.domain.service;

import br.com.autoescola.domain.dto.instrucao.InstrucaoCreateDTO;
import br.com.autoescola.domain.dto.instrucao.InstrucaoResponseDTO;
import br.com.autoescola.domain.model.Aluno;
import br.com.autoescola.domain.model.Instrucao;
import br.com.autoescola.domain.model.Instrutor;
import br.com.autoescola.domain.repository.AlunoRepository;
import br.com.autoescola.domain.repository.InstrucaoRepository;
import br.com.autoescola.domain.repository.InstrutorRepository;
import br.com.autoescola.domain.rules.ValidadorAgendamento;
import br.com.autoescola.infra.exception.AlunoNotFoundException;
import br.com.autoescola.infra.exception.EspecialidadeException;
import br.com.autoescola.infra.exception.InstrutorNotFoundException;
import br.com.autoescola.infra.exception.InvalidDateException;
import jakarta.validation.Valid;
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

    public InstrucaoResponseDTO agendar(@Valid InstrucaoCreateDTO dados) throws AlunoNotFoundException {
        if(!alunoRepository.existsById(dados.idAluno())){
            throw new AlunoNotFoundException("Id do aluno informado não existe.");
        }

        if(dados.idInstrutor()!=null && !instrutorRepository.existsById(dados.idInstrutor())){
            throw new InstrutorNotFoundException("Id do instrutor informado não existe.");
        }

        validadoresAgendamento
                .forEach(v -> v
                        .validar(dados));

        Aluno aluno = alunoRepository.getReferenceById(dados.idAluno());
        Instrutor instrutor = escolherInstrutor(dados);
        if(instrutor == null){
            throw new InvalidDateException("Não existe instrutor disponível para adata / hora informada!");
        }
        Instrucao instrucao = new Instrucao(null, aluno, instrutor, dados.data());
        instrucaoRepository.save(instrucao);
        return new InstrucaoResponseDTO(instrucao);
    }

    private Instrutor escolherInstrutor(InstrucaoCreateDTO dados){
        if(dados.idInstrutor()!=null){
            return instrutorRepository.getReferenceById(dados.idInstrutor());
        }

        if(dados.especialidade()==null){
            throw new EspecialidadeException("Especialidade é obrigatória quando Instrutor não for escolhido.");
        }

        return instrutorRepository.escolherInstrutorAleatorioDisponivel(dados.especialidade(), dados.data());
    }
}
