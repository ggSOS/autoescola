package br.com.autoescola.domain.dto.aluno;

import br.com.autoescola.domain.dto.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record AlunoUpdateDTO(
        @NotNull
        Long id,
        
        String nome,

        String telefone,
    
        EnderecoDTO endereco) {

}
