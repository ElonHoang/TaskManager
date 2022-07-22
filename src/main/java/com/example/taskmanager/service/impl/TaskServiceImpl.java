package com.example.taskmanager.service.impl;


import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

//    @Override
//    public Task createTask(Task task) {
//        taskRepository.save(task);
//        return task;
//    }
//
//    @Override
//    public Task updateTask(Task task) {
//        Task t = taskRepository.findById((long) task.getId()).get();
//        t.setTitle(task.getTitle());
//        t.setDescription(task.getDescription());
//        t.setStatus(task.getStatus());
//        taskRepository.save(t);
//        return t;
//    }
//
//    @Override
//    public Optional<Task> getTaskById(int id) {
//        return taskRepository.findById((long) id).isPresent() ? taskRepository.findById((long) id) : Optional.empty();
//    }
//
//    @Override
//    public void deleteTaskById(int taskId) {
//        taskRepository.deleteById((long) taskId);
//    }

    @Override
    public List<Task> findAll() {

        return taskRepository.findAll();
    }
}
