package br.com.autoescola.exception.type.aluno;

public class AlunoNotFoundException extends Exception {
    public AlunoNotFoundException(String message) {
        super(message);
    }

    public AlunoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
