    package com.example.Teste.controller;

    import com.example.Teste.models.Task;
    import com.example.Teste.services.TaskServices;
    import com.example.Teste.services.UserServices;
    import jakarta.validation.Valid;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.validation.annotation.Validated;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

    import java.net.URI;
    import java.util.List;
    @CrossOrigin(origins = "http://localhost:63342")
    @RestController
    @RequestMapping("/task")
    @Validated
    public class TaskController {

        @Autowired
        private TaskServices taskServices;

        @GetMapping("/id")
        public ResponseEntity<Task> findById(@PathVariable Long id) {
            Task task = taskServices.findById(id);
            return ResponseEntity.ok(task);
        }
        @PostMapping
        @Validated
        public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
            this.taskServices.create(task);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(task.getId()).toUri();
            return ResponseEntity.created(uri).body(task);
        }

        @PutMapping("/{id}")
        @Validated
        public ResponseEntity<Void> updateTask(@Valid @RequestBody Task task, @PathVariable Long id) {
            task.setId(id);
            this.taskServices.update(task);
            return ResponseEntity.ok().build();
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
            this.taskServices.delete(id);
            return ResponseEntity.noContent().build();
        }
        @CrossOrigin(origins = "http://127.0.0.1:5500")
        @GetMapping("/user/{userId}")
        public ResponseEntity <List<Task>> findAllByUserId(@PathVariable Long userId) {
            List<Task> tasks = taskServices.findAllByUserId(userId);
            return ResponseEntity.ok().body(tasks);
        }

    }
