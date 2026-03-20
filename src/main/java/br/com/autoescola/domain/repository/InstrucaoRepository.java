package br.com.autoescola.domain.repository;

import br.com.autoescola.domain.model.Instrucao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrucaoRepository extends JpaRepository<Instrucao, Long> {
}
