package com.intern_project.test_management_service.services;

import com.intern_project.test_management_service.models.Role;
import com.intern_project.test_management_service.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getRoles() {

        return roleRepository.findAll();
    }

    public Role addRole(Role role) {

        return roleRepository.save(role);
    }
}