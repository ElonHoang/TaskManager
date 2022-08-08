package com.example.taskmanager.service.impl;

import com.example.taskmanager.mapper.UserMapper;
import com.example.taskmanager.model.User;
import com.example.taskmanager.model.UserDetail;
import com.example.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser = userMapper.findUserByUsername(username);
        if(findUser == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetail(findUser);
    }


    @Override
    public Integer createUser(User user) {
        return userMapper.insertUser(user);


    }

    @Override
    public boolean getUserByUsername(String username) {
        User findUser = userMapper.findUserByUsername(username);
        if (findUser == null) return false;
        if (findUser.getUsername().equals(username)) {
            return true;
        }
        return false;
    }


}
