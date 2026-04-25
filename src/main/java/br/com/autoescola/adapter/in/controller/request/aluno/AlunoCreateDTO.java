package br.com.autoescola.adapter.in.controller.request.aluno;

import br.com.autoescola.adapter.in.controller.request.endereco.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AlunoCreateDTO(
        @NotBlank @Size(max = 100) String nome,

        @NotBlank @Email String email,

        @NotBlank @Pattern(regexp = "\\d{10,11}") String telefone,

        @NotBlank @Pattern(regexp = "\\d{11}") String cpf,

        @NotNull @Valid EnderecoDTO endereco) {
}
