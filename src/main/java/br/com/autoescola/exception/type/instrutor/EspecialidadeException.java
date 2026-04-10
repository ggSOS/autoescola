package br.com.autoescola.exception.type.instrutor;

public class EspecialidadeException extends RuntimeException {
    public EspecialidadeException(String message) {
        super(message);
    }
    public EspecialidadeException(String message, Throwable cause) {
        super(message, cause);
    }
}
