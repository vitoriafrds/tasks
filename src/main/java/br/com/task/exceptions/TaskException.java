package br.com.task.exceptions;

import lombok.Getter;

@Getter
public class TaskException extends RuntimeException {

    private String mensagem;

    public TaskException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }
}
