package br.com.autoescola.adapter.out.repository.persistance;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.autoescola.adapter.out.repository.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    UsuarioEntity findByLogin(String login);
}
