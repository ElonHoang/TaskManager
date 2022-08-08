package com.example.taskmanager.model;

import com.example.taskmanager.model.ulti.CharacterAnno;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id")
    private int id;

    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @Column(name = "name")
    private String name;
    @CharacterAnno(message = "Username input must be [0,9],[a-z],[A-Z]")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(name = "username")
    private String username;
    @Size(min = 6, max = 70, message = "Password must be between 6 and 70 characters")
    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }


}
