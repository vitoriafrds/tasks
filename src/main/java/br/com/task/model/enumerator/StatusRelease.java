package br.com.task.model.enumerator;

import lombok.Getter;

@Getter
public enum StatusRelease {

    ABERTA("S"),
    ENCERRADA("N");

    private String codigo;

    StatusRelease(String codigo) {
        this.codigo = codigo;
    }
}
