package br.com.autoescola.exception.type.instrutor;

public class InstrutorNotFoundException extends RuntimeException {
    public InstrutorNotFoundException(String message) {
        super(message);
    }

    public InstrutorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
