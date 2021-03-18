package br.com.task.service;

import br.com.task.model.dto.TarefaRequest;
import br.com.task.model.dto.TarefaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    void criarTarefa(TarefaRequest tarefaRequest);
    void concluirTarefa(String id);
    void prepararTarefa(String id);

    TarefaResponse detalharTarefa(String id);
    Page<TarefaResponse> listarTarefas(String categoria, String status, Pageable pageable);
}
