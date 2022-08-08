package com.example.taskmanager.service.impl;

import com.example.taskmanager.mapper.TaskMapper;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskMapper taskMapper;

    @Override
    public Integer createTask(Task task) {
        return taskMapper.insertTask(task);
    }

    @Override
    public Integer updateTask(Task task) {
        return taskMapper.updateTask(task);
    }

    @Override
    public Optional<Task> getTaskById(int id) {
        return taskMapper.selectTaskById(id).isPresent() ? taskMapper.selectTaskById(id) : Optional.empty();
    }

    @Override
    public void deleteTaskById(int taskId) {
        taskMapper.deleteTask(taskId);
    }

    @Override
    public List<Task> findAll() {
        return taskMapper.findAll();
    }

    @Override
    public Page<Task> showAllPage(Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 6);
        List<Task> tasks = taskMapper.findAllPage(pageable);
        return new PageImpl<>(tasks, pageable, taskMapper.countPages(pageable));
    }

    @Override
    public Page<Task> searchTaskTitle(String title, int page) {
        Pageable pageable = PageRequest.of(page - 1, 6);
        List<Task> tasks = taskMapper.searchTasksByTitle(title, pageable);
        return new PageImpl<>(tasks, pageable, taskMapper.countPages(pageable));
    }


    @Override
    public Page<Task> selectTaskByTaskStatusAndTitle(String status, String title, int page) {
        Pageable pageable = PageRequest.of(page - 1, 6);
        Page<Task> tasks;
        List<Task> taskList;
        switch (status) {
            case "OPEN":
                taskList = taskMapper.searchTasksByTitleAndStatus(title, TaskStatus.OPEN.getValue(), pageable);
                tasks = new PageImpl<>(taskList, pageable, taskMapper.countPagesByTitleAndStatus(title, status));
                break;
            case "DONE":
                taskList = taskMapper.searchTasksByTitleAndStatus(title, TaskStatus.DONE.getValue(), pageable);
                tasks = new PageImpl<>(taskList, pageable, taskMapper.countPagesByTitleAndStatus(title, status));
                break;
            case "INPROGRESS":
                taskList = taskMapper.searchTasksByTitleAndStatus(title, TaskStatus.INPROGRESS.getValue(), pageable);
                tasks = new PageImpl<>(taskList, pageable, taskMapper.countPagesByTitleAndStatus(title, status));
                break;
            default:
                taskList = taskMapper.searchTasksByTitle(title, pageable);
                tasks = new PageImpl<>(taskList, pageable, taskMapper.countPagesByTitle(title));
                break;
        }
        return tasks;
    }

    @Override
    public Integer countPagesByTitleAndStatus(String title, String status) {
        return taskMapper.countPagesByTitleAndStatus(title, status);
    }

    @Override
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Task_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
        List<Task> listUsers = taskMapper.findAll();
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Task ID", "Title", "Description", "Status"};
        String[] nameMapping = {"id", "title", "description", "status"};

        csvWriter.writeHeader(csvHeader);

        for (Task task : listUsers) {
            csvWriter.write(task, nameMapping);
        }
        csvWriter.close();
    }
}
