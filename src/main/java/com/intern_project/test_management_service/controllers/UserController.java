package com.intern_project.test_management_service.controllers;

import com.intern_project.test_management_service.models.User;
import com.intern_project.test_management_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final JdbcTemplate jdbcTemplate;
    private final UserService userService;

    @GetMapping("/get-users")
    public List<User> getUsers() {

        return userService.getUsers();
    }

    @PostMapping("/create-user")
    public User createUser(@RequestBody User user) {

        return userService.addUser(user);
    }

    @GetMapping("/get-user-by-id")
    public User getUserById(@RequestParam("id") Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/delete-user/{id}")
    public void deleteUserById(@PathVariable Long id) {
        // Delete user roles associated with the user
        String deleteUserRolesSql = "DELETE FROM user_roles WHERE user_id = ?";
        jdbcTemplate.update(deleteUserRolesSql, id);

        // Delete the user
        String deleteUserSql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(deleteUserSql, id);
    }
}
