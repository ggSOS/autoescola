package br.com.autoescola.application.core.usecase;

import br.com.autoescola.adapter.in.controller.mapper.InstrutorMapper;
import br.com.autoescola.adapter.in.controller.request.instrutor.InstrutorCreateDTO;
import br.com.autoescola.adapter.in.controller.request.instrutor.InstrutorUpdateDTO;
import br.com.autoescola.adapter.in.controller.response.instrutor.DadosDetalhamentoInstrutorDTO;
import br.com.autoescola.adapter.in.controller.response.instrutor.ListagemInstrutorDTO;
import br.com.autoescola.adapter.out.repository.entity.InstrutorEntity;
import br.com.autoescola.adapter.out.repository.mapper.InstrutorEntityMapper;
import br.com.autoescola.adapter.out.repository.persistance.InstrutorRepository;
import br.com.autoescola.application.core.domain.model.Instrutor;
import br.com.autoescola.exception.type.instrutor.InstrutorNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstrutorService {

    private final InstrutorRepository repository;
    private final InstrutorMapper mapper;
    private final InstrutorEntityMapper entityMapper;

    @Transactional
    public DadosDetalhamentoInstrutorDTO cadastrarInstrutor(InstrutorCreateDTO dados) {
        Instrutor instrutor = mapper.toDomain(dados);
        InstrutorEntity entity = repository.save(entityMapper.toEntity(instrutor));
        return mapper.toResponseDTO(entityMapper.toDomain(entity));
    }

    public Page<ListagemInstrutorDTO> listarInstrutores(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao)
                .map(entityMapper::toDomain)
                .map(instrutor -> new ListagemInstrutorDTO(
                        instrutor.getId(),
                        instrutor.getNome(),
                        instrutor.getEmail(),
                        instrutor.getCnh(),
                        instrutor.getEspecialidade()
                ));
    }

    public DadosDetalhamentoInstrutorDTO detalharInstrutor(Long id) {
        return repository.findById(id)
                .map(entityMapper::toDomain)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new InstrutorNotFoundException("Instrutor não encontrado com o id: " + id));
    }

    @Transactional
    public DadosDetalhamentoInstrutorDTO atualizarInstrutor(Long id, InstrutorUpdateDTO dados) {
        InstrutorEntity entity = repository.findById(id)
                .orElseThrow(() -> new InstrutorNotFoundException("Instrutor não encontrado com o id: " + id));
        Instrutor instrutor = entityMapper.toDomain(entity);
        instrutor.atualizarInformacoes(dados.nome(), dados.telefone(), dados.endereco() != null ? new br.com.autoescola.application.core.domain.vo.EnderecoVO(dados.endereco()) : null);
        repository.save(entityMapper.toEntity(instrutor));
        return mapper.toResponseDTO(instrutor);
    }

    @Transactional
    public void deletarInstrutor(Long id) {
        InstrutorEntity entity = repository.findById(id)
                .orElseThrow(() -> new InstrutorNotFoundException("Instrutor não encontrado com o id: " + id));
        Instrutor instrutor = entityMapper.toDomain(entity);
        instrutor.excluir();
        repository.save(entityMapper.toEntity(instrutor));
    }
}
