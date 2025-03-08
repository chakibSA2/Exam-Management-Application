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

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Roles role;

    @Column(name = "active")
    private Boolean active;

    public Users() {
    }

    public Users(String username, String firstName, String lastName, String email, String password, Roles role, Boolean active) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    @PrePersist
    private void generateUserId() {
        if (this.userId == null) {
            this.userId = generateUserId(this.role);
        }
    }

    private String generateUserId(Roles role) {
        String prefix = switch (role) {
            case ADMIN -> "ADM";
            case TEACHER -> "ENS";
            case STUDENT -> "ETD";
            default -> throw new IllegalArgumentException("Rôle inconnu : " + role);
        };
        String uniquePart = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        return prefix + uniquePart;
    }

    public String getUserId() {
        return userId;
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

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
