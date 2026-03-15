package br.com.autoescola.domain.dto.usuario;

import br.com.autoescola.domain.enums.Role;
import br.com.autoescola.domain.model.Usuario;

public record DadosDetalhamentoUsuarioDTO(
    Long id,
    String login,
    Role perfil
) {
    public DadosDetalhamentoUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getUsername(), usuario.getPerfil());
    }
}
