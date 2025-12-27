package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {


    @Query("SELECT u FROM User u WHERE u.username = :username AND u.role = :role")
    List<User> findByUsernameAndRole(
        @Param("username") String username,
        @Param("role") String role
    );

    List<User> findByRole(String role);
}
