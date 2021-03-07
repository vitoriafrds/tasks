package br.com.task.model.dto;

import br.com.task.model.Tarefa;
import br.com.task.model.enumerator.StatusTarefa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TarefaResponse {

    private String id;
    private String categoria;
    private String descricao;
    private StatusTarefa status;
    private LocalDate inicio;
    private LocalDate conclusao;

    public TarefaResponse(Tarefa tarefa) {
        this.id = tarefa.getId();
        this.categoria = tarefa.getCategoria();
        this.descricao = tarefa.getDescricao();
        this.status = StatusTarefa.parse(tarefa.getStatus());
        this.inicio = tarefa.getDataInicio();
        this.conclusao = tarefa.getDataConclusao() != null ? tarefa.getDataConclusao() : null;
    }
}
