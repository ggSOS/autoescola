package br.com.autoescola.adapter.out.repository.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.autoescola.application.core.domain.model.Aluno;
import org.springframework.data.jpa.repository.Query;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Page<Aluno> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select e.ativo
            from Aluno e
            Where
            e.id = :id
            """)
    Boolean findAtivoById(Long id);// findByIdAndAtivoTrue nao usaria query
}
