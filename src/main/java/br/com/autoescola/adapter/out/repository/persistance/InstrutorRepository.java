package br.com.autoescola.adapter.out.repository.persistance;

import br.com.autoescola.application.core.domain.enums.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.autoescola.application.core.domain.model.Instrutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {

    Page<Instrutor> findAllByAtivoTrue(Pageable paginacao);

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
    Instrutor escolherInstrutorAleatorioDisponivel(Especialidade especialidade, LocalDateTime data);
}
