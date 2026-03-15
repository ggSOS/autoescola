package br.com.autoescola.domain.model;

import br.com.autoescola.domain.dto.DadosAtualizacaoInstrutor;
import br.com.autoescola.domain.dto.InstrutorDTO;
import br.com.autoescola.domain.enums.Especialidade;
import br.com.autoescola.domain.vo.EnderecoVO;
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

@Table(name = "instrutores")
@Entity(name = "Instrutor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Instrutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean ativo;
    private String nome;
    private String email;
    private String cnh;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private EnderecoVO endereco;

    public Instrutor(InstrutorDTO dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.cnh = dados.cnh();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new EnderecoVO(dados.endereco());
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoInstrutor dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
