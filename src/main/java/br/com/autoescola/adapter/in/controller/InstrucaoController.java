package br.com.autoescola.adapter.in.controller;

import br.com.autoescola.adapter.in.controller.request.instrucao.InstrucaoCreateDTO;
import br.com.autoescola.adapter.in.controller.response.instrucao.InstrucaoResponseDTO;
import br.com.autoescola.application.core.usecase.InstrucaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instrucoes")
public class InstrucaoController {

    private final InstrucaoService agenda;

    @PostMapping
    public ResponseEntity<InstrucaoResponseDTO> agendarInstrucao(@RequestBody @Valid InstrucaoCreateDTO dados){
        InstrucaoResponseDTO dto = agenda.agendar(dados);
        return ResponseEntity
                .ok()
                .build();
    }
}
