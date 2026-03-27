package br.com.autoescola.domain.rules;

import br.com.autoescola.domain.dto.instrucao.InstrucaoCreateDTO;
import br.com.autoescola.domain.repository.AlunoRepository;
import br.com.autoescola.infra.exception.AlunoInativo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidadorAlunoAtivo implements ValidadorAgendamento {

    private final AlunoRepository repository;


    @Override
    public void validar(InstrucaoCreateDTO dados) {
        Boolean alunoAtivo = repository.findAtivoById(dados.idAluno());

        if (!alunoAtivo){
            throw new AlunoInativo("Instrucao nao pode ser agendada para aluno inativo!");
        }
    }
}
