package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/all")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/task")
    public String getAllTask(Model model) {
        List<Task> task = taskService.findAll();
        model.addAttribute("show", task);
        return "show";
//        return taskService.findAll();
    }
    @GetMapping("/task{id}")
    public Optional<Task> getTaskById(int id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/post")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task t = taskService.createTask(task);
        return new ResponseEntity(t, HttpStatus.CREATED);
    }

//    @PutMapping("/put{id}")
//    public ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable("id") int id) {
//        Optional<Task> findId = taskService.getTaskById(id);
//        ret
//    }

    @GetMapping("/task/page")
    public String pagination(Model model, @RequestParam("p") Optional<Integer> p) {

//        Pageable pageable =  PageRequest.of(p.orElse(0),7);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Task> page = taskService.getPage((PageRequest) pageable);
        model.addAttribute("show", page);
        return "show";
    }


}
