package br.com.autoescola.exception.type.instrucao;

public class InstrucaoNotFoundException extends RuntimeException {
    public InstrucaoNotFoundException(String message) {
        super(message);
    }
}
