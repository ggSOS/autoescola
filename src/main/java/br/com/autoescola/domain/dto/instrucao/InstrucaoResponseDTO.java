package br.com.autoescola.domain.dto.instrucao;

import br.com.autoescola.domain.model.Instrucao;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record InstrucaoResponseDTO(
        Long id,
        String nomeAluno,
        String nomeInstrutor,
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
        LocalDateTime data) {
    public InstrucaoResponseDTO(Instrucao instrucao){
        this(
                instrucao.getId(),
                instrucao.getAluno().getNome(),
                instrucao.getInstrutor().getNome(),
                instrucao.getData());
    }
}
