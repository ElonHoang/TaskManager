package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.service.UserService;
import com.example.taskmanager.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authen")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String goToLogin(Model model)
    {
        model.addAttribute("acc",new User());
        return "login";
    }
    @GetMapping("/register")
    public String goToRegister(Model model){
        model.addAttribute("user",new User());
        return "register";
    }
    @GetMapping("/get")
    public String show(Model model){
        model.addAttribute("user",userService.findAll());

        return "test";
    }
    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, BindingResult rs){
        if(userService.getUserByString(user.getUserName()) == true){
            rs.addError(new FieldError("user","userName","UserName is already exists !"));
            return "register";
        }
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String pass = encode.encode(user.getPassWord());
        user.setPassWord(pass);
            userService.createUser(user);
            return "redirect:/authen/login" ;
        }
}
