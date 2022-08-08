package com.example.taskmanager.model;

import com.example.taskmanager.model.ulti.CharacterAnno;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "task_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CharacterAnno(message = "Title input must be [0,9],[a-z],[A-Z]")
    @Size(min = 3, max = 250, message = "Title must be between 3 and 250 characters")
    private String title;
    @Size(min = 3, max = 250, message = "Description must be between 3 and 250 characters")
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private TaskStatus status;

    public Task() {

    }

    public Task(int id, String title, String description, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }


}
