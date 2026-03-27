package br.com.autoescola.infra.exception;

public class AlunoNotFoundException extends Exception {
    public AlunoNotFoundException(String message) {
        super(message);
    }

    public AlunoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
