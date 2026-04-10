package br.com.autoescola.adapter.out.repository.mapper;

import br.com.autoescola.adapter.in.controller.request.endereco.EnderecoDTO;
import br.com.autoescola.application.core.domain.vo.EnderecoVO;

public class EnderecoMapper {
    public EnderecoVO EnderecoToDomain(EnderecoDTO dados){
        return new EnderecoVO(
                dados.logradouro(),
                dados.numero(),
                dados.complemento(),
                dados.bairro(),
                dados.cidade(),
                dados.uf(),
                dados.cep()
        );
    }
}
