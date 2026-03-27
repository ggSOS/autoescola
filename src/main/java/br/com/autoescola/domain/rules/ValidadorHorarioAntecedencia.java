package br.com.autoescola.domain.rules;

import br.com.autoescola.domain.dto.instrucao.InstrucaoCreateDTO;
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
