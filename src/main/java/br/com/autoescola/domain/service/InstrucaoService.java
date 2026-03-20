package br.com.autoescola.domain.service;

import br.com.autoescola.domain.dto.instrucao.InstrucaoCreateDTO;
import br.com.autoescola.domain.dto.instrucao.InstrucaoResponseDTO;
import br.com.autoescola.domain.model.Aluno;
import br.com.autoescola.domain.model.Instrucao;
import br.com.autoescola.domain.model.Instrutor;
import br.com.autoescola.domain.repository.AlunoRepository;
import br.com.autoescola.domain.repository.InstrucaoRepository;
import br.com.autoescola.domain.repository.InstrutorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstrucaoService{

    private final AlunoRepository alunoRepository;
    private final InstrutorRepository instrutorRepository;
    private final InstrucaoRepository instrucaoRepository;

    public InstrucaoResponseDTO agendar(@Valid InstrucaoCreateDTO dados) {
        Aluno aluno = alunoRepository.getReferenceById(dados.idAluno());
        Instrutor instrutor = instrutorRepository.getReferenceById((dados.idInstrutor()));
        Instrucao instrucao = new Instrucao(null, aluno, instrutor, dados.data());
        instrucaoRepository.save(instrucao);
        return new InstrucaoResponseDTO(instrucao);
    }
}
