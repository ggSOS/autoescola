package br.com.autoescola.application.core.usecase;

import br.com.autoescola.adapter.in.controller.request.aluno.AlunoCreateDTO;
import br.com.autoescola.adapter.in.controller.request.aluno.AlunoUpdateDTO;
import br.com.autoescola.application.core.domain.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlunoServicePort {
    Page<Aluno> listarAlunos(Pageable paginacao);
    Aluno detalharAluno(Long id);
    Aluno cadastrarAluno(AlunoCreateDTO dados);
    Aluno atualizarAluno(Long id, AlunoUpdateDTO dados);
    void deletarAluno(Long id);
}
