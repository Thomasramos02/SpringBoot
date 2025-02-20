package com.example.Teste.services;

import com.example.Teste.models.User;
import com.example.Teste.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServices {

    @Autowired
   private UserRepositories userRepositories;


    public User findById(Long id) {
        Optional<User> user = this.userRepositories.findById(id);
        return user.orElseThrow(() -> new RuntimeException("Usuario não encontrado! Id: " + id+ "\n Tipo:"+ User.class.getName()));
    }

    @Transactional
    public User create(User user) {
        user.setId(null);
        this.userRepositories.save(user);;
        return user;
    }

    @Transactional
    public User update(User user) {
        User newUser = this.findById(user.getId());
        newUser.setPassword(user.getPassword());
        return this.userRepositories.save(newUser);
    }

    public void delete(Long id) {
        this.findById(id);
        try {
            this.userRepositories.deleteById(id);
        }catch (Exception e) {
            throw new RuntimeException("Nao foi possivel deletar o usuario: "+id+"\n pois" +
                    "tem entidades relacionadas");
        }
    }

}
