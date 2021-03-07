package br.com.task.service.impl;

import br.com.task.model.Tarefa;
import br.com.task.model.dto.TarefaRequest;
import br.com.task.model.dto.TarefaResponse;
import br.com.task.model.enumerator.StatusTarefa;
import br.com.task.repository.TarefaRepository;
import br.com.task.service.TarefaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TarefaServiceImpl implements TarefaService {

    private TarefaRepository repository;

    @Autowired
    public TarefaServiceImpl(TarefaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void criarTarefa(TarefaRequest tarefaRequest) {
        Tarefa tarefa = new Tarefa();
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
        repository.findById(id).ifPresent(action -> {
            action.setDataConclusao(LocalDate.now());
            action.setStatus(StatusTarefa.CONCLUIDA.getDescricao());
            repository.save(action);
        });

        log.info("Tarefa {} finalizada", id);
    }

    @Override
    public TarefaResponse detalharTarefa(String id) {
        TarefaResponse detalheTarefa = new TarefaResponse();

        repository.findById(id).ifPresent(tarefa -> {
            detalheTarefa.setId(tarefa.getId());
            detalheTarefa.setCategoria(tarefa.getCategoria());
            detalheTarefa.setDescricao(tarefa.getDescricao());
            detalheTarefa.setInicio(tarefa.getDataInicio());
            detalheTarefa.setStatus(StatusTarefa.parse(tarefa.getStatus()));
            detalheTarefa.setConclusao(tarefa.getDataConclusao() != null ? tarefa.getDataConclusao() : null);

        });

        return detalheTarefa;
    }

    @Override
    public Page<TarefaResponse> listarTarefas(String categoria, Pageable pageable) {
        Page<Tarefa> tarefas = repository.findAllByCategoria(categoria, pageable);

        long elementos = tarefas.getTotalElements();

        return new PageImpl<>(tarefas.stream().map(Tarefa::convertToResponse)
                .collect(Collectors.toList()), pageable, elementos);
    }
}
