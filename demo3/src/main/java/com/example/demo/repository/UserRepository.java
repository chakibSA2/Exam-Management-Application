package com.example.demo.repository;

import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, String> {

    List<Users> findByRole(String role);

    @Query("SELECT u.userId FROM Users u WHERE u.role = :role ORDER BY u.userId DESC LIMIT 1")
    String findLastUserIdByRole(@Param("role") String role);
}
