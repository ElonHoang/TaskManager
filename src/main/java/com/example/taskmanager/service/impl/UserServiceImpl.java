package com.example.taskmanager.service.impl;

import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.UserRepository;

import com.example.taskmanager.service.UserDetail;
import com.example.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserDetailsService, UserService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser = userRepository.findByUsername(username);
//        List<GrantedAuthority> grantList = new ArrayList<>();
//        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
//        grantList.add(authority);
//        UserDetails userDetails = new org.springframework.security.core.userdetails.User(findUser.getUserName(),
//                findUser.getPassWord(),grantList);
//
//        return userDetails;
        return new UserDetail(findUser);
    }


    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User userId = userRepository.findById((long) user.getId()).get();
        userId.setName(user.getName());
        userId.setPassWord(user.getPassWord());
        return user;
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById((long) id).isPresent() ? userRepository.findById((long) id) : Optional.empty();
    }

    @Override
    public void deleteUserById(int userId) {
    userRepository.deleteById((long) userId);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }
}
