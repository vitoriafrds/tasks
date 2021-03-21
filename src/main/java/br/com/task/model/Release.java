package br.com.task.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "release_ativa")
public class Release {

    @Id
    @Column(name = "numero_release")
    public String numeroRelease;

    @Column(name = "descricao")
    public String descricao;

    @Column(name = "indicador_release_ativa")
    public String indicadorReleaseAtiva;
}
