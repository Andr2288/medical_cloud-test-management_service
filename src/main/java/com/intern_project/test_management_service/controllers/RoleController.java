package com.intern_project.test_management_service.controllers;

import com.intern_project.test_management_service.models.Role;
import com.intern_project.test_management_service.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RoleController {

    private final JdbcTemplate jdbcTemplate;
    private final RoleService roleService;

    @GetMapping("/getRoles")
    public List<Role> getRoles() {

        return roleService.getRoles();
    }

    @PostMapping("/createRole")
    public Role createRole(@RequestBody Role role) {

        return roleService.addRole(role);
    }

    @DeleteMapping("/deleteRolesAndResetSequence")
    public void deleteAllRoles() {

        String sqlTruncate = "TRUNCATE TABLE roles RESTART IDENTITY";
        jdbcTemplate.update(sqlTruncate);
    }
}
