package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.UserRepository;
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

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/authen")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String goToLogin(Model model) {
        model.addAttribute("acc", new User());
        return "login";
    }

    @PostMapping("/check")
    public String checkLogin(@Valid @ModelAttribute("acc") User user, BindingResult rs) {
        if (rs.hasErrors()) {
            return "login";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = userService.findByPassword(user.getUserName());
        boolean check = encoder.matches(user.getPassWord(), pass);
        if (check == false) {
            rs.addError(new FieldError("acc", "userName", "UserName or PassWord not true !"));
            return "login";
        }
        return "redirect:/all/task";

    }

    @GetMapping("/register")
    public String goToRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute User user, BindingResult rs) {
        if (rs.hasErrors()) {
            return "register";
        }

        if (userService.getUserByString(user.getUserName()) == true) {
            rs.addError(new FieldError("user", "userName", "UserName is already exists !"));
            return "register";
        }
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String pass = encode.encode(user.getPassWord());
        user.setPassWord(pass);
        userService.createUser(user);
        return "redirect:/authen/login";
    }
}
