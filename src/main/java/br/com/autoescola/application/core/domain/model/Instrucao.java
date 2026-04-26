package br.com.autoescola.application.core.domain.model;

import java.time.LocalDateTime;

import br.com.autoescola.application.core.domain.enums.MotivoCancelamento;

public class Instrucao {
    private Long id;
    private Aluno aluno;
    private Instrutor instrutor;
    private LocalDateTime data;
    private MotivoCancelamento motivoCancelamento;

    public Instrucao() {
    }

    public Instrucao(
            Long id,
            Aluno aluno,
            Instrutor instrutor,
            LocalDateTime data,
            MotivoCancelamento motivoCancelamento) {
        this.id = id;
        this.aluno = aluno;
        this.instrutor = instrutor;
        this.data = data;
        this.motivoCancelamento = motivoCancelamento;
    }

    public Long getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public MotivoCancelamento getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void cancelar(MotivoCancelamento motivo) {
        this.motivoCancelamento = motivo;
    }
}
