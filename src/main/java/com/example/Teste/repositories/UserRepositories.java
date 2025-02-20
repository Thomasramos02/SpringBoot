package com.example.Teste.repositories;


import com.example.Teste.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositories extends JpaRepository<User, Long> {
    // ja tem todos os metodos, nao precisa declarar
}
