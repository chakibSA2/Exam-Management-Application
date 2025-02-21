package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
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

    public Optional<Users> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public List<Users> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    public boolean isUserRole(String userId, String role) {
        Optional<Users> user = userRepository.findById(userId);
        return user.map(value -> value.getRole().equalsIgnoreCase(role)).orElse(false);
    }
}
