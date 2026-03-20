package br.com.autoescola.domain.dto.instrucao;

import br.com.autoescola.domain.enums.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record InstrucaoCreateDTO(
        @NotNull
        Long idAluno,

        Long idInstrutor,

        Especialidade especialidade,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
        LocalDateTime data
) {
}
