package br.com.task.repository;

import br.com.task.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    Page<Task> findAllByCategoria(String categoria, Pageable pageable);
    Page<Task> findAllByCategoriaAndStatus(String categoria, String status, Pageable pageable);
    Page<Task> findAllByStatus(String status, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value="UPDATE TAREFA SET STATUS = ?2 WHERE ID_TAREFA = ?1", nativeQuery = true)
    int atualizarStatusTarefa(String id, String status);

    @Modifying
    @Transactional
    @Query(value="UPDATE TAREFA SET STATUS = ?2, SET DATA_INICIO = ?3 WHERE ID_TAREFA = ?1", nativeQuery = true)
    int concluirTarefa(String id, String status, LocalDate data);
}
