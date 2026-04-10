package br.com.autoescola.application.core.validation.agendamento;

import br.com.autoescola.application.core.validation.interfaces.ValidadorAgendamento;
import br.com.autoescola.adapter.in.controller.request.instrucao.InstrucaoCreateDTO;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamento {

    @Override
    public void validar(InstrucaoCreateDTO dados) {
        LocalDateTime agendamento = dados.data();

        boolean domingo = agendamento.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean preAbertura = agendamento.getHour() < 6;
        boolean posFechamento = agendamento.getHour() > (21 - 1);

        if (domingo || preAbertura || posFechamento){
            throw new DateTimeException("Tentativa de agendamento fora do horário de funcionamento.");
        }
    }
}
