package com.intern_project.test_management_service.controllers;

import com.intern_project.test_management_service.models.Role;
import com.intern_project.test_management_service.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/get-roles")
    public List<Role> getRoles() {

        return roleService.getRoles();
    }

    @PostMapping("/create-role")
    public Role createRole(@RequestBody Role role) {

        return roleService.addRole(role);
    }
}
