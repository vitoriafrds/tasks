package br.com.task.exceptions.erros;

import lombok.Getter;

@Getter
public enum ErroAplicacao {

    ERRO_TAREFA_NAO_LOCALIZADA("TAREFA NAO LOCALIZADA");

    private String mensagem;

    ErroAplicacao(String mensagem) {
        this.mensagem = mensagem;
    }
}
