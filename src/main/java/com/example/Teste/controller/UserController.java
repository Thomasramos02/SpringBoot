package com.example.Teste.controller;

import com.example.Teste.models.User;
import com.example.Teste.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserServices userServices;


    //localHost: 8080/user/1
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user = this.userServices.findById(id);
        return ResponseEntity.ok().body(user);
    }
    @PostMapping
    @Validated(User.creatUser.class)
    public ResponseEntity<Void> create(@Valid @RequestBody User user){
        this.userServices.create(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
    @PutMapping("/{id}")
    @Validated(User.updateUser.class)
    public ResponseEntity<Void> update(@Valid @RequestBody User user, @PathVariable Long id){
        user.setId(id);
        this.userServices.update(user);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.userServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}
