package br.com.autoescola.infra.exception;

public class EspecialidadeException extends RuntimeException {
    public EspecialidadeException(String message) {
        super(message);
    }
    public EspecialidadeException(String message, Throwable cause) {
        super(message, cause);
    }
}
