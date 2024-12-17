package com.intern_project.test_management_service.controllers;

import com.intern_project.test_management_service.models.Test;
import com.intern_project.test_management_service.models.TestType;
import com.intern_project.test_management_service.models.User;
import com.intern_project.test_management_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final JdbcTemplate jdbcTemplate;
    private final UserService userService;

    @GetMapping("/getUsers")
    public List<User> getUsers() {

        return userService.getUsers();
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {

        return userService.addUser(user);
    }

    @DeleteMapping("/deleteUsersAndResetSequence")
    public void deleteAllUsers() {

        String sqlTruncate = "TRUNCATE TABLE users RESTART IDENTITY CASCADE";
        jdbcTemplate.update(sqlTruncate);
    }
}
