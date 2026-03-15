package br.com.autoescola.domain.dto.usuario;

import br.com.autoescola.domain.enums.Role;
import jakarta.validation.constraints.NotNull;

public record UsuarioUpdateDTO(
    @NotNull
    Long id,
    String login,
    Role perfil
) {
}
