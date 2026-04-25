package br.com.autoescola.application.core.validation.interfaces;

import br.com.autoescola.adapter.in.controller.request.instrucao.InstrucaoDeleteDTO;

public interface ValidadorCancelamento {
    void validar(InstrucaoDeleteDTO dados);
}
