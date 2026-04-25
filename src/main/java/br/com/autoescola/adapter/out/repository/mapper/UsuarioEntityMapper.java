package br.com.autoescola.adapter.out.repository.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.autoescola.adapter.out.repository.entity.UsuarioEntity;
import br.com.autoescola.application.core.domain.model.Usuario;

@Component
@Service
public class UsuarioEntityMapper {
    public Usuario toDomain(UsuarioEntity dados){
        return new Usuario(
            dados.getId(),
            dados.getLogin(),
            dados.getSenha(),
            dados.getPerfil()
        );
    }

    public UsuarioEntity toEntity(Usuario dados){
        return new UsuarioEntity(
            dados.getId(),
            dados.getLogin(),
            dados.getSenha(),
            dados.getPerfil()
        );
    }
}
