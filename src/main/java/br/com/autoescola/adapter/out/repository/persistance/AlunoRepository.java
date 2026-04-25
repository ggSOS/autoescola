package br.com.autoescola.adapter.out.repository.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.autoescola.adapter.out.repository.entity.AlunoEntity;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {

    Page<AlunoEntity> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select e.ativo
            from Aluno e
            Where
            e.id = :id
            """)
    Boolean findAtivoById(Long id);
}
