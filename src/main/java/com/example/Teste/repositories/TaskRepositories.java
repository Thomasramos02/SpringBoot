package com.example.Teste.repositories;


import com.example.Teste.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepositories extends JpaRepository<Task, Long> {
    //ja possui todos os metodos,ou seja, nao precisa declarar nenhum metodo

    // lista de tarefa do usuario
    List<Task> findByUser_id(Long id);
}
    