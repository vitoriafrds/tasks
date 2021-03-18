package br.com.task.service.impl;

import br.com.task.exceptions.TaskException;
import br.com.task.exceptions.erros.ErroAplicacao;
import br.com.task.model.Task;
import br.com.task.model.dto.TarefaRequest;
import br.com.task.model.dto.TarefaResponse;
import br.com.task.model.enumerator.StatusTarefa;
import br.com.task.repository.TaskRepository;
import br.com.task.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository repository;

    @Autowired
    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public void criarTarefa(TarefaRequest tarefaRequest) {
        Task tarefa = new Task();
        tarefa.setId(UUID.randomUUID().toString());
        tarefa.setCategoria(tarefaRequest.getCategoria());
        tarefa.setDescricao(tarefaRequest.getDescricao());
        tarefa.setStatus(StatusTarefa.EM_ANDAMENTO.getDescricao());
        tarefa.setDataInicio(LocalDate.now());

        log.info("Tarefa de ID: {} criada com sucesso", tarefa.getId());
        repository.save(tarefa);
    }

    @Override
    public void concluirTarefa(String id) {
        repository.findById(id).ifPresent(x ->
                repository.concluirTarefa(id, StatusTarefa.CONCLUIDA.getDescricao(), LocalDate.now()));

        log.info("Tarefa {} finalizada", id);
    }

    @Override
    public void prepararTarefa(String id) {
        repository.findById(id).ifPresent(y ->
                repository.atualizarStatusTarefa(id, StatusTarefa.AGUARDANDO_IMPLANTACAO.getDescricao()));

        log.info("Tarefa [{}] aguardando implantacao");
    }

    @Override
    public TarefaResponse detalharTarefa(String id) {
        return repository.findById(id).map(Task::convertToResponse).
                orElseThrow(() ->
                        new TaskException(ErroAplicacao.ERRO_TAREFA_NAO_LOCALIZADA.getMensagem()));
    }

    @Override
    public Page<TarefaResponse> listarTarefas(String categoria, String status, Pageable pageable) {
        Page<Task> tarefas = montarConsulta(categoria, status, pageable);

        long elementos = tarefas.getTotalElements();

        return new PageImpl<>(tarefas.stream().map(Task::convertToResponse)
                .collect(Collectors.toList()), pageable, elementos);
    }

    private Page<Task> montarConsulta(String categoria, String status, Pageable pageable) {
        Page<Task> tarefas = null;

        if (StringUtils.hasText(categoria) && StringUtils.hasText(status)) {
            tarefas = repository.findAllByCategoriaAndStatus(categoria, status, pageable);

        } else if (StringUtils.hasText(categoria)) {
            tarefas = repository.findAllByCategoria(categoria, pageable);

        } else if (StringUtils.hasText(status)) {
            tarefas = repository.findAllByStatus(status, pageable);
        }

        return tarefas;
    }
}
