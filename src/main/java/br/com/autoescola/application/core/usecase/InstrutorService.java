package br.com.autoescola.application.core.usecase;

import br.com.autoescola.adapter.in.controller.mapper.InstrutorMapper;
import br.com.autoescola.adapter.in.controller.response.instrutor.DadosDetalhamentoInstrutorDTO;
import br.com.autoescola.adapter.in.controller.request.instrutor.InstrutorCreateDTO;
import br.com.autoescola.adapter.in.controller.response.instrutor.ListagemInstrutorDTO;
import br.com.autoescola.application.core.domain.model.Instrutor;
import br.com.autoescola.adapter.out.repository.persistance.InstrutorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstrutorService {

    private final InstrutorRepository repository;
    private final InstrutorMapper mapper;

    @Transactional
    public DadosDetalhamentoInstrutorDTO cadastrarInstrutores(@Valid InstrutorCreateDTO dados) {
        Instrutor instrutor = mapper.toDomain(dados);;
        repository.save(instrutor);
        return mapper.toDetailsDTO(instrutor);
    }

    public Page<DadosListagemInstrutor> listarInstrutores(Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(mapper::toListDTO);
    }

    public DadosDetalhamentoInstrutorDTO atualizarInstrutor(){
        return ;
    }
}
