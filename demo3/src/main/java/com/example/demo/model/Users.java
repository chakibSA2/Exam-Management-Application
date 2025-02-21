package com.example.demo.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false) // ADMIN, TEACHER, STUDENT
    private String role;

    @Column(name = "active")
    private Boolean active;

    public Users() {}

    public Users(String username, String firstName, String lastName, String email, String password, String role, Boolean active) {
        this.userId = generateUserId(role);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    private String generateUserId(String role) {
        String prefix;
        switch (role.toUpperCase()) {
            case "ADMIN":
                prefix = "ADM";
                break;
            case "TEACHER":
                prefix = "ENS";
                break;
            case "STUDENT":
                prefix = "ETD";
                break;
            default:
                throw new IllegalArgumentException("Rôle inconnu : " + role);
        }
        String uniquePart = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        return prefix + uniquePart;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}


