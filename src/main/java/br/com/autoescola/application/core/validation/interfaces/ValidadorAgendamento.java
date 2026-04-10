package br.com.autoescola.application.core.validation.interfaces;

import br.com.autoescola.adapter.in.controller.request.instrucao.InstrucaoCreateDTO;

public interface ValidadorAgendamento {
    void validar(InstrucaoCreateDTO dados);

}
