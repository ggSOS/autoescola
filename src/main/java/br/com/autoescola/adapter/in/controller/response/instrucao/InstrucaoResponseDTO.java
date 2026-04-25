package br.com.autoescola.adapter.in.controller.response.instrucao;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record InstrucaoResponseDTO(
        Long id,
        String nomeAluno,
        String nomeInstrutor,
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm") LocalDateTime data) {
}
