package br.com.autoescola.application.core.domain.model;

import br.com.autoescola.adapter.in.controller.request.aluno.AlunoCreateDTO;
import br.com.autoescola.adapter.in.controller.request.aluno.AlunoUpdateDTO;
import br.com.autoescola.application.core.domain.vo.EnderecoVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private EnderecoVO endereco;
    private Boolean ativo;

    public Aluno(AlunoCreateDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new EnderecoVO(dados.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(AlunoUpdateDTO dados){
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
