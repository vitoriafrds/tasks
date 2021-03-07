package br.com.task.controller;

import br.com.task.model.dto.TarefaRequest;
import br.com.task.model.dto.TarefaResponse;
import br.com.task.service.impl.TarefaServiceImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tarefas")
public class TarefaController {

    private TarefaServiceImpl service;

    @Autowired
    public TarefaController(TarefaServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> criarTarefa(@RequestBody TarefaRequest tarefa) {
        service.criarTarefa(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/detalhe")
    public ResponseEntity<TarefaResponse> detalharTarefa(@PathVariable String id) {
        return ResponseEntity.ok().body(service.detalharTarefa(id));
    }

    @GetMapping
    public ResponseEntity<Page<TarefaResponse>> listar(
            @RequestParam String categoria,
            @RequestParam int page,
            @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<TarefaResponse> tarefas = service.listarTarefas(categoria, pageable);

        if (tarefas.getContent().isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(tarefas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> concluir(@PathVariable String id) {
        service.concluirTarefa(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
