package com.example.taskmanager.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id")
    private int id;
    @Size(min = 3, max = 50, message = "name min >= 3 and max <= 50")
    @Column(name = "name")
    private String name;
    @Size(min = 3, max = 50, message = "userName min >= 3 and max <= 50")
    @Column(name = "username")
    private String userName;
    @Size(min = 3, max = 250, message = "passWord min >= 3 and max <= 250")
    @Column(name = "password")
    private String passWord;

    public User() {
    }

    public User(int id, String name, String userName, String passWord) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && name.equals(user.name) && userName.equals(user.userName) && passWord.equals(user.passWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userName, passWord);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
