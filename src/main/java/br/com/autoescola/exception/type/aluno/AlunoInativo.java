package br.com.autoescola.exception.type.aluno;

public class AlunoInativo extends RuntimeException {
    public AlunoInativo(String message) {
        super(message);
    }
    public AlunoInativo(String message, Throwable cause) {
        super(message, cause);
    }
}
