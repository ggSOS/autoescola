package br.com.autoescola.application.core.validation.agendamento;

import br.com.autoescola.application.core.validation.interfaces.ValidadorAgendamento;
import br.com.autoescola.adapter.in.controller.request.instrucao.InstrucaoCreateDTO;
import br.com.autoescola.adapter.out.repository.persistance.AlunoRepository;
import br.com.autoescola.exception.type.aluno.AlunoInativo;
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
