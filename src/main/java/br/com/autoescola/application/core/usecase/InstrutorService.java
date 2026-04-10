package br.com.autoescola.application.core.usecase;

import br.com.autoescola.adapter.in.controller.response.instrutor.DadosDetalhamentoInstrutorDTO;
import br.com.autoescola.adapter.in.controller.request.instrutor.InstrutorCreateDTO;
import br.com.autoescola.application.core.domain.model.Instrutor;
import br.com.autoescola.adapter.out.repository.persistance.InstrutorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstrutorService {

    private final InstrutorRepository repository;

    @Transactional
    public DadosDetalhamentoInstrutorDTO cadastrar(@Valid InstrutorCreateDTO dados) {
        Instrutor instrutor = new Instrutor(dados);
        repository.save(instrutor);
        return new DadosDetalhamentoInstrutorDTO(instrutor);
    }
}
