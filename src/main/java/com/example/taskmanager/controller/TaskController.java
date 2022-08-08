package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/all")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/tasks")
    public String showTasks(Model model) {

        return pagination(model, 1, "", "");
    }

    @GetMapping("/task/{id}")
    public Optional<Task> getTaskById(int id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/create-task")
    public String goToAddTask(Model model) {
        model.addAttribute("task", new Task());
        return "addTask";
    }

    @PostMapping("/create")
    public String createTask(@Valid Task task, BindingResult rs) {
        if (rs.hasErrors()) {
            return "addTask";
        }
        task.setStatus(TaskStatus.OPEN);
        taskService.createTask(task);
        return "redirect:/all/tasks";
    }

    @GetMapping("/edit-task/{id}")
    public String goToEditTask(Model model, @PathVariable(value = "id") int id) {
        Optional<Task> edit = taskService.getTaskById(id);
        model.addAttribute("task", edit);
        return "editTask";
    }

    @PostMapping("/edit")
    public String editTask(@Valid Task task, BindingResult rs) {
        if (rs.hasErrors()) {
            return "editTask";
        }
        taskService.updateTask(task);
        return "redirect:/all/tasks";
    }

    @GetMapping("/detail-task/{id}")
    public String detailTask(Model model, @PathVariable(value = "id") int id) {
        Optional<Task> detail = taskService.getTaskById(id);
        model.addAttribute("task", detail);
        return "detailTask";
    }

    @GetMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable(value = "id") int id) {
        taskService.deleteTaskById(id);
        return "redirect:/all/tasks";
    }

    @GetMapping("/search")
    public String searchByTitle(Model model, @Param("title") String title, @Param("status") String status) {
        return pagination(model, 1, title, status);
    }

    @GetMapping("/task/page/{page}")
    public String pagination(Model model, @PathVariable("page") Integer page, @Param("title") String title, @Param("status") String status) {
        Page<Task> taskPage = taskService.selectTaskByTaskStatusAndTitle(status, title, page);
        model.addAttribute("tasks", taskPage.getContent());
        model.addAttribute("totals", taskPage.getTotalPages());
        model.addAttribute("" + "", page);
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        return "tasks";
    }

    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse httpServletResponse) throws IOException {
        taskService.exportToCSV(httpServletResponse);
    }


}
