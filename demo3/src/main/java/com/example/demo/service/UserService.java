package com.example.demo.service;

import com.example.demo.model.Roles;
import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    public List<Users> getAllTeachers() {
        return userRepository.findByRole(Roles.TEACHER);
    }

    public List<Users> getAllStudents() {
        return userRepository.findByRole(Roles.STUDENT);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public Optional<Users> getUserByUsername(String username) { return userRepository.findByUsername(username); }

    public List<Users> getUsersByRole(Roles role) {
        return userRepository.findByRole(role);
    }

    public boolean isUserRole(String userId, Roles role) {
        Optional<Users> user = userRepository.findById(userId);
        return user.map(value -> value.getRole().equals(role)).orElse(false);
    }

    public boolean deleteUserById(String userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public Users updateUser(Users user) {
        Users existingUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));

        existingUser.setUsername(user.getUsername());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        existingUser.setActive(user.getActive());

        return userRepository.save(existingUser);
    }
}
