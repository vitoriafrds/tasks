package br.com.task.repository;

import br.com.task.model.Tarefa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, String> {
    Page<Tarefa> findAllByCategoria(String categoria, Pageable pageable);
}
