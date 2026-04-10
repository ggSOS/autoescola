package br.com.autoescola.adapter.in.controller.request.instrutor;

import br.com.autoescola.adapter.in.controller.request.endereco.EnderecoDTO;
import br.com.autoescola.application.core.domain.enums.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record InstrutorCreateDTO(
    // @NotBlank serve para null e "", é mais que @NotNull ent, recomendado para Strings
    @NotBlank    
    String nome,

    @NotBlank
    @Email
    String email,

    @NotBlank
    String telefone,

    // "\\d{2}/\\{2}/\\d{4}" = campo numerico com 2 algarismos/campo numerico com 2 algarismos/campo numerico com 4 algarismos
    // "\\d{9,11}" = campo numerico entre 9 e 11 algarismos
    @NotBlank
    @Pattern(regexp = "\\d{9,11}")
    String cnh,

    @NotNull
    Especialidade especialidade,

    // @Valid faz checagem dos atributos de endereco conforme estabelecido na classe
    @NotNull
    @Valid
    EnderecoDTO endereco
    ) {
}
