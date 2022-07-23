package com.example.taskmanager.repository;


import com.example.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository
        //extends JpaRepository<User,Long>
        extends CrudRepository<User,Long>
{
    //@Query("SELECT  u FROM User u WHERE u.userName = ?1")
    @Query("SELECT  u FROM User u WHERE u.userName = :username")
    public User findByUsername(@Param("username") String username);
}
