package br.com.autoescola.adapter.out.repository.persistance;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.autoescola.adapter.out.repository.entity.InstrutorEntity;
import br.com.autoescola.application.core.domain.enums.Especialidade;

public interface InstrutorRepository extends JpaRepository<InstrutorEntity, Long> {

    Page<InstrutorEntity> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select i from Instrutor i
            where
            i.ativo = true
            and
            i.especialidade = :especialidade
            and
            i.id not in (
                select a.instrutor.id from Instrucao a
                where
                a.data =:data
                )
            order by rand()
            limit 1
            """)
    InstrutorEntity escolherInstrutorAleatorioDisponivel(Especialidade especialidade, LocalDateTime data);
}
