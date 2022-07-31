package com.example.taskmanager.service.impl;


import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        taskRepository.save(task);
        return task;
    }

    @Override
    public Task updateTask(Task task) {
        Task t = taskRepository.findById(task.getId()).get();
        t.setTitle(task.getTitle());
        t.setDescription(task.getDescription());
        t.setStatus(task.getStatus());
        taskRepository.save(t);
        return t;
    }

    @Override
    public Optional<Task> getTaskById(int id) {
        return taskRepository.findById(id).isPresent() ? taskRepository.findById(id) : Optional.empty();
    }

    @Override
    public void deleteTaskById(int taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<Task> findAll() {

        return taskRepository.findAll();
    }

    @Override
    public Page<Task> getPage(Pageable page) {
        return taskRepository.findAll(page);
    }

    @Override
    public List<Task> searchTaskByTitle(String title,String task) {
        if(title == null){
            return taskRepository.findAll() ;
        }
        List<Task> taskList = null;
        switch (task){
            case "OPEN":
                taskList =  taskRepository.selectTaskByTaskStatus(TaskStatus.OPEN,title);
                break;
            case "DONE":
                taskList = taskRepository.selectTaskByTaskStatus(TaskStatus.DONE,title);
                break;
            case "INPROGRESS":
                taskList = taskRepository.selectTaskByTaskStatus(TaskStatus.INPROGRESS,title);
                break;
            default:
                taskList = taskRepository.searchTaskTitle(title);
                break;
        }
        return taskList;
    }

//    @Override
//    public List<Task> selectTaskByTaskStatus(String task) {
//        List<Task> taskList = null;
//        switch (task){
//            case "OPEN":
//                taskList =  taskRepository.selectTaskByTaskStatus(TaskStatus.OPEN);
//                break;
//            case "DONE":
//                taskList = taskRepository.selectTaskByTaskStatus(TaskStatus.DONE);
//                break;
//            case "INPROGRESS":
//                taskList = taskRepository.selectTaskByTaskStatus(TaskStatus.INPROGRESS);
//                break;
//            default:
//                taskList = taskRepository.findAll();
//                break;
//        }
//        return taskList;
//    }
}
