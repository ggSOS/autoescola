package br.com.autoescola.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.autoescola.domain.model.Instrutor;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {

    Page<Instrutor> findAllByAtivoTrue(Pageable paginacao);
}
