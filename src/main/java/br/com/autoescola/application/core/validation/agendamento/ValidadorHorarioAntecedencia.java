package br.com.autoescola.application.core.validation.agendamento;

import br.com.autoescola.application.core.validation.interfaces.ValidadorAgendamento;
import br.com.autoescola.adapter.in.controller.request.instrucao.InstrucaoCreateDTO;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamento {
    @Override
    public void validar(InstrucaoCreateDTO dados) {
        LocalDateTime agendamento = dados.data();
        LocalDateTime agora = LocalDateTime.now();

        Long antecedencia = Duration.between(agora, agendamento).toMinutes();

        if (antecedencia<30){
            throw new DateTimeException("O agendamento deve ser feito com antecedência mínima de 30 minutos.");
        }
    }
}
