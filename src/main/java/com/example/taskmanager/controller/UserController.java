package com.example.taskmanager.controller;

import com.example.taskmanager.model.User;
import com.example.taskmanager.service.UserService;
import com.example.taskmanager.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/authen")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String goToLogin(Model model){
        model.addAttribute("login");
        return "login";
    }
    @GetMapping("/register")
    public String goToRegister(){

        return "register";
    }
    @PostMapping("/add")
    public String getAll(@ModelAttribute User user){

        userService.createUser(user);
        return "redirect:/login" ;
    }
}
