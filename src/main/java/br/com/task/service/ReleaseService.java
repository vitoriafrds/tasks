package br.com.task.service;

import br.com.task.model.Release;
import br.com.task.model.dto.ReleaseResponse;
import br.com.task.model.enumerator.StatusRelease;
import br.com.task.model.enumerator.StatusTarefa;
import br.com.task.repository.ReleaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ReleaseService {

    private ReleaseRepository repository;

    @Autowired
    public ReleaseService(ReleaseRepository repository) {
        this.repository = repository;
    }

    public void abrirRelease() {
        Release release = new Release();
        release.setNumeroRelease(gerarNumeroRelease());
        release.setIndicadorReleaseAtiva(StatusRelease.ABERTA.getCodigo());

        log.info("Release {} gerada", release.getNumeroRelease());
        repository.save(release);
    }

    private String gerarNumeroRelease() {
        String prefixo = "RLSE";
        int initialIndex = 0;
        int lastIndex = 7;
        String key = UUID.randomUUID().toString();

        return prefixo.concat(key.substring(initialIndex, lastIndex)).toUpperCase();
    }

    @Transactional
    public void encerrarRelease() {
        int resultado = repository.encerrarRelease();

        if (resultado == 0) {
            log.warn("Nenhuma release ativa encontrada para encerramento");
        } else {
            log.info("Encerrando release com sucesso...");
        }
    }

    public ReleaseResponse exibirNumeroReleaseAtiva() {
        Optional<Release> releaseAtiva = repository.findByIndicadorReleaseAtiva(StatusRelease.ABERTA.getCodigo());
        return releaseAtiva.map(release -> ReleaseResponse.builder().releaseAtiva(release.getNumeroRelease()).build()).orElse(null);

    }
}
