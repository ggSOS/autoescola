package br.com.autoescola.domain.dto;

import br.com.autoescola.domain.enums.Especialidade;
import br.com.autoescola.domain.model.Instrutor;

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
