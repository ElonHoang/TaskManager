package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    }
    @GetMapping("/task{id}")
    public Optional<Task> getTaskById(int id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/add")
    public String goToAdd(Model model){
        model.addAttribute("task",new Task());
        return "addTask";
    }

    @PostMapping("/post")
    public String createTask(@Valid @ModelAttribute("task") Task task, BindingResult rs)  {
            if(rs.hasErrors()){
                return "addTask";
            }
            taskService.createTask(task);
            return "redirect:/all/task";

    }
    @GetMapping ("/edit/{id}")
    public String editTask(@Valid Model model, @PathVariable(value = "id") int id){
//        if(rs.hasErrors()){
//            return "editTask";
//        }
        Optional<Task> edit = taskService.getTaskById(id);
        model.addAttribute("task",edit);
        //return "redirect:/all/task";
        return "editTask";
    }
    @GetMapping("/status")
    public String getAllListStatus(Model model){
        List<TaskStatus> list = null;
        for(TaskStatus t : TaskStatus.values()){
            list.add(t);
        }
    }

    @GetMapping("/task/page")
    public String pagination(Model model, @RequestParam("p") Optional<Integer> p) {

//        Pageable pageable =  PageRequest.of(p.orElse(0),7);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Task> page = taskService.getPage((PageRequest) pageable);
        model.addAttribute("show", page);
        return "show";
    }


}
