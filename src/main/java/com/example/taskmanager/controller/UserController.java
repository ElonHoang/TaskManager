package com.example.taskmanager.controller;

import com.example.taskmanager.model.User;
import com.example.taskmanager.service.UserService;
import com.example.taskmanager.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String goToLogin(){
        return "login";
    }
    @GetMapping("/register")
    public String goToRegister(Model model){
        model.addAttribute("user",new User());
        return "register";
    }
    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, BindingResult rs){

        userService.createUser(user,rs);
        if(rs.hasErrors()){
            return "register";
        }

        return "redirect:/authen/login" ;
    }
}
