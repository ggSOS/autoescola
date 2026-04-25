package br.com.autoescola.application.core.usecase;

import br.com.autoescola.adapter.in.controller.request.aluno.AlunoCreateDTO;
import br.com.autoescola.adapter.in.controller.request.aluno.AlunoUpdateDTO;
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
public class AlunoService implements AlunoServicePort {

    private final AlunoRepository repository;
    private final AlunoEntityMapper mapper;

    @Override
    public Page<Aluno> listarAlunos(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(mapper::toDomain);
    }

    @Override
    public Aluno detalharAluno(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new AlunoNotFoundException("Aluno não encontrado com o id: " + id));
    }

    @Override
    @Transactional
    public Aluno cadastrarAluno(AlunoCreateDTO dados) {
        Aluno aluno = new Aluno(dados);
        AlunoEntity entity = repository.save(mapper.toEntity(aluno));
        return mapper.toDomain(entity);
    }

    @Override
    @Transactional
    public Aluno atualizarAluno(Long id, AlunoUpdateDTO dados) {
        AlunoEntity entity = repository.findById(id)
                .orElseThrow(() -> new AlunoNotFoundException("Aluno não encontrado com o id: " + id));
        Aluno aluno = mapper.toDomain(entity);
        aluno.atualizarInformacoes(dados);
        repository.save(mapper.toEntity(aluno));
        return aluno;
    }

    @Override
    @Transactional
    public void deletarAluno(Long id) {
        AlunoEntity entity = repository.findById(id)
                .orElseThrow(() -> new AlunoNotFoundException("Aluno não encontrado com o id: " + id));
        Aluno aluno = mapper.toDomain(entity);
        aluno.excluir();
        repository.save(mapper.toEntity(aluno));
    }
}
