package br.com.task.repository;

import br.com.task.model.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, String> {

    Optional<Release> findByIndicadorReleaseAtiva(String indicadorReleaseAtiva);

    @Modifying
    @Query(value = "UPDATE RELEASE_ATIVA SET indicador_release_ativa = 'N' WHERE indicador_release_ativa = 'S'", nativeQuery = true)
    int encerrarRelease();
}
