package br.com.task.controller;

import br.com.task.model.dto.ReleaseResponse;
import br.com.task.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/release")
public class ReleaseController {

    public ReleaseService service;

    @Autowired
    public ReleaseController(ReleaseService service) {
        this.service = service;
    }

    @GetMapping("/ativa")
    public ResponseEntity<ReleaseResponse> exibirReleaseAtiva() {
        return ResponseEntity.ok().body(service.exibirNumeroReleaseAtiva());
    }

    @PostMapping
    public ResponseEntity<Void> gerarRelease() {
        service.abrirRelease();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/end")
    public ResponseEntity<Void> encerrarRelease() {
        service.encerrarRelease();;
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
