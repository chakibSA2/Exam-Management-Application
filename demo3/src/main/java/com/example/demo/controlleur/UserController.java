package com.example.demo.controlleur;

import com.example.demo.model.Roles;
import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable String userId) {
        Optional<Users> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<Users>> getUsersByRole(@PathVariable Roles role) {
        List<Users> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }
}
