package br.com.task.model;

import br.com.task.model.dto.TarefaResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TAREFA")
public class Task {

    @Id
    @Column(name = "ID_TAREFA", length = 70)
    private String id;

    @Column(name = "TIPO_CATEGORIA")
    private String categoria;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "RLSE")
    private String release;

    @Column(name = "DATA_INICIO")
    private LocalDate dataInicio;

    @Column(name = "DATA_CONCLUSAO")
    private LocalDate dataConclusao;

    public static TarefaResponse convertToResponse(Task tarefa) {
        return new TarefaResponse(tarefa);
    }
}
