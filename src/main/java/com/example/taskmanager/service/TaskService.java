package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task createTask(Task task);
    Task updateTask(Task task);
    Optional<Task> getTaskById(int id);
    void deleteTaskById(int taskId);
    List<Task> findAll();
    Page<Task> getPage(Pageable page);
}
