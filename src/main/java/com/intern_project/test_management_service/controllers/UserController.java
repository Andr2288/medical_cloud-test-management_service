package com.intern_project.test_management_service.controllers;

import com.intern_project.test_management_service.models.User;
import com.intern_project.test_management_service.repositories.UserRepository;
import com.intern_project.test_management_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/get-users")
    public List<User> getUsers() {

        return userService.getUsers();
    }

    @PostMapping("/create-user")
    public User createUser(@RequestBody User user) {

        return userService.addUser(user);
    }

    @GetMapping("/get-user-by-id/{id}")
    public User getUserById(@PathVariable Long id) {

        return userService.getUserById(id);
    }

    @DeleteMapping("/delete-user-by-id/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setDeleteDate(LocalDateTime.now());
            userRepository.save(user);
            return ResponseEntity.ok("User deleted successfully (soft delete)");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PutMapping("/restore-user-by-id/{id}")
    public ResponseEntity<String> restoreUserById(@PathVariable Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setDeleteDate(null);
            userRepository.save(user);

            return ResponseEntity.ok("User restored successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}