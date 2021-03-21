package br.com.task.model.enumerator;

import lombok.Getter;

@Getter
public enum StatusTarefa {

    EM_ANDAMENTO("EM ANDAMENTO"),
    AGUARDANDO_IMPLANTACAO("AGUARDANDO IMPLANTACAO"),
    CONCLUIDA("CONCLUIDA");

    private String descricao;

    StatusTarefa(String descricao) {
        this.descricao = descricao;
    }

    public static StatusTarefa parse(String status) {
        if (StatusTarefa.CONCLUIDA.getDescricao().equals(status)) {
            return CONCLUIDA;
        } else if (StatusTarefa.AGUARDANDO_IMPLANTACAO.getDescricao().equals(status)) {
            return AGUARDANDO_IMPLANTACAO;
        } else {
            return CONCLUIDA;
        }
    }
}
