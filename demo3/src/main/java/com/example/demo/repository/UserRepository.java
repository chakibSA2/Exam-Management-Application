package com.example.demo.repository;

import com.example.demo.model.Roles;
import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String> {

    List<Users> findByRole(Roles role);
    List<Users> findAll();

    @Query("SELECT u FROM Users u WHERE u.role = :role ORDER BY u.userId DESC")
    List<Users> findLastUserIdByRole(@Param("role") Roles role, Pageable pageable);

    Optional<Users> findByEmail(String email);
}
