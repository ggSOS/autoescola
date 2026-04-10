package br.com.autoescola.adapter.in.controller.response.instrutor;

import br.com.autoescola.application.core.domain.enums.Especialidade;
import br.com.autoescola.application.core.domain.model.Instrutor;

public record ListagemInstrutorDTO(
        Long id,
        String nome,
        String email,
        String cnh,
        Especialidade especialidade) {
            public ListagemInstrutorDTO(Instrutor instrutor){
                this(instrutor.getId(), instrutor.getNome(), instrutor.getEmail(), instrutor.getCnh(), instrutor.getEspecialidade());
            }
}
