package br.com.autoescola.adapter.out.repository.persistance;

import br.com.autoescola.application.core.domain.model.Instrucao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrucaoRepository extends JpaRepository<Instrucao, Long> {
}
