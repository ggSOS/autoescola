package br.com.autoescola.application.core.validation.cancelamento;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;

import br.com.autoescola.adapter.in.controller.request.instrucao.InstrucaoDeleteDTO;
import br.com.autoescola.adapter.out.repository.persistance.InstrucaoRepository;
import br.com.autoescola.application.core.validation.interfaces.ValidadorCancelamento;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidadorHorarioAntecedencia implements ValidadorCancelamento {
    
    private final InstrucaoRepository repository;

    @Override
    public void validar(InstrucaoDeleteDTO dados) {
        var instrucao = repository.getReferenceById(dados.id());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, instrucao.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new DateTimeException("Instrução somente pode ser cancelada com antecedência mínima de 24 horas!");
        }
    }
}
