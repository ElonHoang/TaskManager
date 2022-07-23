package com.example.taskmanager.service;



import com.example.taskmanager.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService  {
    User createUser(User user);
    User updateUser(User user);
    Optional<User> getUserById(int id);
    void deleteUserById(int userId);
    List<User> findAll();
}
