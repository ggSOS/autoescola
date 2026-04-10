package br.com.autoescola.adapter.out.repository.mapper;

import br.com.autoescola.adapter.in.controller.request.instrutor.InstrutorCreateDTO;
import br.com.autoescola.application.core.domain.model.Instrutor;
import br.com.autoescola.application.core.domain.vo.EnderecoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InstrutorMapper {
    private final EnderecoMapper enderecoMapper;

    public void toDomain(InstrutorCreateDTO dados) {
        return new Instrutor(
                null,
                true,
                dados.nome(),
                dados.email(),
                dados.cnh(),
                dados.telefone(),
                dados.especialidade(),
                dados.endereco()
                );
    }
}
