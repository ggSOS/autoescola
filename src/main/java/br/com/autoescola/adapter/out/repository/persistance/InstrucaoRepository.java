package br.com.autoescola.adapter.out.repository.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.autoescola.adapter.out.repository.entity.InstrucaoEntity;

public interface InstrucaoRepository extends JpaRepository<InstrucaoEntity, Long> {
}
