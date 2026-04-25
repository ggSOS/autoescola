package br.com.autoescola.application.core.domain.model;

import br.com.autoescola.adapter.in.controller.request.aluno.AlunoCreateDTO;
import br.com.autoescola.adapter.in.controller.request.aluno.AlunoUpdateDTO;
import br.com.autoescola.application.core.domain.vo.EnderecoVO;

public class Aluno {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private EnderecoVO endereco;
    private Boolean ativo;

    public Aluno() {
    }

    public Aluno(
            Long id,
            String nome,
            String email,
            String telefone,
            String cpf,
            EnderecoVO endereco,
            Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.endereco = endereco;
        this.ativo = ativo;
    }

    public Aluno(AlunoCreateDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new EnderecoVO(dados.endereco());
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public EnderecoVO getEndereco() {
        return endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void atualizarInformacoes(AlunoUpdateDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(
                    endereco.getLogradouro(),
                    endereco.getNumero(),
                    endereco.getComplemento(),
                    endereco.getBairro(),
                    endereco.getCidade(),
                    endereco.getUf(),
                    endereco.getCep());
        }
    }

    public void excluir() {
        this.nome = "unknown";
        this.cpf = "unknown";
        this.ativo = false;
    }
}
