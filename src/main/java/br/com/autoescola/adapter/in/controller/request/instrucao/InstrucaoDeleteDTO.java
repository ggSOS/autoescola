package br.com.autoescola.adapter.in.controller.request.instrucao;

import br.com.autoescola.application.core.domain.enums.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record InstrucaoDeleteDTO(
        @NotNull Long id,

        @NotNull MotivoCancelamento motivoCancelamento) {
}
