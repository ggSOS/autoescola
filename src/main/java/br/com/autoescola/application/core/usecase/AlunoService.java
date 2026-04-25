package br.com.autoescola.application.core.usecase;

import br.com.autoescola.adapter.in.controller.mapper.AlunoDTOMapper;
import br.com.autoescola.adapter.in.controller.request.aluno.AlunoCreateDTO;
import br.com.autoescola.adapter.in.controller.request.aluno.AlunoUpdateDTO;
import br.com.autoescola.adapter.in.controller.response.aluno.AlunoDetailedResponseDTO;
import br.com.autoescola.adapter.in.controller.response.aluno.AlunoResponseDTO;
import br.com.autoescola.adapter.out.repository.entity.AlunoEntity;
import br.com.autoescola.adapter.out.repository.mapper.AlunoEntityMapper;
import br.com.autoescola.adapter.out.repository.persistance.AlunoRepository;
import br.com.autoescola.application.core.domain.model.Aluno;
import br.com.autoescola.exception.type.aluno.AlunoNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository repository;
    private final AlunoDTOMapper dtoMapper;
    private final AlunoEntityMapper entityMapper;

    @Transactional
    public AlunoDetailedResponseDTO cadastrarAluno(AlunoCreateDTO dados) {
        Aluno aluno = dtoMapper.toDomain(dados);
        AlunoEntity entity = repository.save(entityMapper.toEntity(aluno));
        return dtoMapper.toDetailedResponseDTO(entityMapper.toDomain(entity));
    }

    public Page<AlunoResponseDTO> listarAlunos(Pageable paginacao) {
        return repository
                .findAllByAtivoTrue(paginacao)
                .map(entityMapper::toDomain)
                .map(dtoMapper::toResponseDTO);
    }

    public AlunoDetailedResponseDTO detalharAluno(Long id) {
        return repository.findById(id)
                .map(entityMapper::toDomain)
                .map(dtoMapper::toDetailedResponseDTO)
                .orElseThrow(() -> new AlunoNotFoundException(
                        "Aluno não encontrado com o id: " + id));
    }

    @Transactional
    public AlunoDetailedResponseDTO atualizarAluno(Long id, AlunoUpdateDTO dados) {
        AlunoEntity entity = repository.findById(id)
                .orElseThrow(() -> new AlunoNotFoundException(
                    "Aluno não encontrado com o id: " + id));
        Aluno aluno = entityMapper.toDomain(entity);
        aluno.atualizarInformacoes(dados);
        repository.save(entityMapper.toEntity(aluno));
        return dtoMapper.toDetailedResponseDTO(aluno);
    }


    @Transactional
    public void deletarAluno(Long id) {
        AlunoEntity entity = repository.findById(id)
                .orElseThrow(() -> new AlunoNotFoundException(
                    "Aluno não encontrado com o id: " + id));
        Aluno aluno = entityMapper.toDomain(entity);
        aluno.excluir();
        repository.save(entityMapper.toEntity(aluno));
    }
}
