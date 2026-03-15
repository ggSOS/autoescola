package br.com.autoescola.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(
    @NotBlank
    String logradouro,

    String numero,

    String complemento,

    @NotBlank
    String bairro,

    @NotBlank
    String cidade,

    // "[a-z A-Z]{2}" = campo de String com 2 chars de a-z maiúsculo ou minúsculo(diferente de A-z, ler tabela ASCII)
    @NotBlank
    @Pattern(regexp = "[a-z A-Z]{2}")
    String uf,

    // "[0-9]{8}" = campo de String com 8 chars de 0-9
    @NotBlank
    @Pattern(regexp = "[0-9]{8}")
    String cep
    ) {
}
