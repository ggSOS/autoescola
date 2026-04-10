package br.com.autoescola.application.core.domain.model;

import br.com.autoescola.adapter.in.controller.request.instrutor.InstrutorUpdateDTO;
import br.com.autoescola.adapter.in.controller.request.instrutor.InstrutorCreateDTO;
import br.com.autoescola.application.core.domain.enums.Especialidade;
import br.com.autoescola.application.core.domain.vo.EnderecoVO;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class Instrutor {
    private Long id;
    private Boolean ativo;
    private String nome;
    private String email;
    private String cnh;
    private String telefone;
    private Especialidade especialidade;
    private EnderecoVO endereco;

    public Instrutor() {
    }

    public Instrutor(Long id, Boolean ativo, String nome, String email, String cnh, String telefone, Especialidade especialidade, EnderecoVO endereco) {
        this.id = id;
        this.ativo = ativo;
        this.nome = nome;
        this.email = email;
        this.cnh = cnh;
        this.telefone = telefone;
        this.especialidade = especialidade;
        this.endereco = endereco;
    }

    public Instrutor(InstrutorCreateDTO dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.cnh = dados.cnh();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new EnderecoVO(dados.endereco());
    }

    public void atualizarInformacoes(@Valid InstrutorUpdateDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
