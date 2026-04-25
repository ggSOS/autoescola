package br.com.autoescola.application.core.domain.vo;

import br.com.autoescola.adapter.in.controller.request.endereco.EnderecoDTO;

public class EnderecoVO {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public EnderecoVO() {
    }

    public EnderecoVO(
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String uf,
            String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    public EnderecoVO(EnderecoDTO dados) {
        this.logradouro = dados.logradouro();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.bairro = dados.bairro();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
        this.cep = dados.cep();
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getCep() {
        return cep;
    }

    public void atualizarInformacoes(
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String uf,
            String cep) {
        if (logradouro != null) {
            this.logradouro = logradouro;
        }
        if (numero != null) {
            this.numero = numero;
        }
        if (complemento != null) {
            this.complemento = complemento;
        }
        if (bairro != null) {
            this.bairro = bairro;
        }
        if (cidade != null) {
            this.cidade = cidade;
        }
        if (uf != null) {
            this.uf = uf;
        }
        if (cep != null) {
            this.cep = cep;
        }
    }
}
