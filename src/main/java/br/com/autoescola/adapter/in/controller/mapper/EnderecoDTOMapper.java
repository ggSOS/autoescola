package br.com.autoescola.adapter.in.controller.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.autoescola.adapter.in.controller.request.endereco.EnderecoDTO;
import br.com.autoescola.application.core.domain.vo.EnderecoVO;

@Component
@Service
public class EnderecoDTOMapper {
    public EnderecoVO toDomain(EnderecoDTO dados) {
        return new EnderecoVO(
                dados.logradouro(),
                dados.numero(),
                dados.complemento(),
                dados.bairro(),
                dados.cidade(),
                dados.uf(),
                dados.cep());
    }

    public EnderecoDTO toDTO(EnderecoVO dados) {
        return new EnderecoDTO(
                dados.getLogradouro(),
                dados.getNumero(),
                dados.getComplemento(),
                dados.getBairro(),
                dados.getCidade(),
                dados.getUf(),
                dados.getCep());
    }
}
