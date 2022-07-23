package com.example.taskmanager.controller;

import com.example.taskmanager.service.UserService;
import com.example.taskmanager.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/authen")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/login")
    public String goToLogin(Model model){
        model.addAttribute("login");
        return "login";
    }
    @GetMapping("/register")
    public String goToRegister(Model model){
        model.addAttribute("register");
        return "register";
    }
//    @GetMapping("/getAll")
//    public String getAll(){
//        userService.findAll();
//        return "OK" ;
//    }
}
