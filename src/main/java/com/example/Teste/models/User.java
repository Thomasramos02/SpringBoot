package com.example.Teste.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;


@Entity
@Table(name = User.tableName)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class User {

    public interface creatUser{}
    public interface updateUser{}


    public static final String tableName = "user";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false,unique = true)
    @NotNull(groups = creatUser.class)
    @NotEmpty(groups = creatUser.class)
    @Size(groups = creatUser.class ,min = 2, max = 100)
    private String username;

    @Column(name = "password", length = 60, nullable = false)
    @NotNull(groups = {creatUser.class, updateUser.class})
    @NotEmpty(groups = {creatUser.class, updateUser.class})
    @Size(groups = {creatUser.class, updateUser.class}, min = 8, max = 60)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();


    // getter e setter de Task
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @JsonIgnore
    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


}
