package br.com.autoescola.domain.service;

import br.com.autoescola.domain.dto.instrutor.DadosDetalhamentoInstrutorDTO;
import br.com.autoescola.domain.dto.instrutor.InstrutorCreateDTO;
import br.com.autoescola.domain.model.Instrutor;
import br.com.autoescola.domain.repository.InstrutorRepository;
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
