package com.example.Teste.services;

import com.example.Teste.models.Task;
import com.example.Teste.models.User;
import com.example.Teste.repositories.TaskRepositories;
import com.example.Teste.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServices {
    @Autowired
    private UserServices userServices;
    @Autowired
    private TaskRepositories taskRepositories;

    public Task findById(Long id){
        Optional<Task> task = taskRepositories.findById(id);
        return task.orElseThrow(()-> new RuntimeException("Task nao foi encontrada Id:"+id+"\n Tipo: "+Task.class.getName()));
    }

    public List<Task> findAllByUserId(Long id){
        List<Task> tasks = this.taskRepositories.findByUser_id(id);
        return tasks;
    }

    @Transactional
    public Task create(Task task){
       User user = this.userServices.findById(task.getUser().getId());
       task.setUser(user);
       task = this.taskRepositories.save(task);
       return task;
    }

    @Transactional
    public Task update(Task task){
        Task newObj = findById(task.getId());
        newObj.setDescription(task.getDescription());
        return this.taskRepositories.save(newObj);
    }

    public void delete(Long id){
        findById(id);
        taskRepositories.deleteById(id);
    }

}
