package com.example.taskmanager.service;

import com.example.taskmanager.model.User;

public interface UserService  {
    Integer createUser(User user);
    boolean getUserByUsername(String name);
}
