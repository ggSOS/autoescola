package br.com.autoescola.infra.exception;

public class AlunoInativo extends RuntimeException {
    public AlunoInativo(String message) {
        super(message);
    }
    public AlunoInativo(String message, Throwable cause) {
        super(message, cause);
    }
}
