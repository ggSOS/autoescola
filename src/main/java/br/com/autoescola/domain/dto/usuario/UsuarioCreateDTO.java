package br.com.autoescola.domain.dto.usuario;

import br.com.autoescola.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioCreateDTO(
    @NotBlank
    String login,
    @NotBlank
    String senha,
    @NotNull
    Role perfil
) {
}
