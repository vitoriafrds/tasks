package br.com.task.model.enumerator;

import lombok.Getter;

@Getter
public enum StatusTarefa {

    EM_ANDAMENTO("EM ANDAMENTO"),
    CONCLUIDA("CONCLUIDA");

    private String descricao;

    StatusTarefa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusTarefa parse(String status) {
        if (StatusTarefa.CONCLUIDA.getDescricao().equals(status)) {
            return CONCLUIDA;
        } else {
            return EM_ANDAMENTO;
        }
    }
}
