package com.example.taskmanager.repository;


import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    @Query("SELECT u FROM Task u WHERE u.title LIKE %?1% ")
    public List<Task> searchTaskTitle(String title);
    @Query("SELECT u FROM Task u WHERE u.status = ?1")
    public List<Task> selectTaskByTaskStatus(TaskStatus task);
}
