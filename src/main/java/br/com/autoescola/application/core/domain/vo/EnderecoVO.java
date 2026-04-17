package br.com.autoescola.application.core.domain.vo;

import br.com.autoescola.adapter.in.controller.request.endereco.EnderecoDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoVO {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    
    public EnderecoVO(EnderecoDTO dados) {
        this.logradouro = dados.logradouro();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.bairro = dados.bairro();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
        this.cep = dados.cep();
    }

    public void atualizarInformacoes(
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String uf,
            String cep
    ) {
        if(logradouro != null){
            this.logradouro = logradouro;
        }
        if(numero != null){
            this.numero = numero;
        }
        if(complemento != null){
            this.complemento = complemento;
        }
        if(bairro != null){
            this.bairro = bairro;
        }
        if(cidade != null){
            this.cidade = cidade;
        }
        if(uf != null){
            this.uf = uf;
        }
        if(cep != null){
            this.cep = cep;
        }
    }
}
