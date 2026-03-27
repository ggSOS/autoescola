package br.com.autoescola.domain.rules;

import br.com.autoescola.domain.dto.instrucao.InstrucaoCreateDTO;

public interface ValidadorAgendamento {
    void validar(InstrucaoCreateDTO dados);

}
