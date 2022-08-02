package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Integer createTask(Task task);

    Integer updateTask(Task task);

    Optional<Task> getTaskById(int id);

    void deleteTaskById(int taskId);

    List<Task> findAll();

    List<Task> findAllTask();

    public Page<Task> searchTaskTitle(String title, int page);

    public List<Task> selectTaskByTaskStatusAndTitle(String task, String title, int page);
}
