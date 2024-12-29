package com.intern_project.test_management_service.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intern_project.test_management_service.models.Role;
import com.intern_project.test_management_service.services.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RoleControllerTest {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_CLIENT = "ROLE_CLIENT";
    public static final String ROLE_LAB_TECHNICIAN = "ROLE_LAB_TECHNICIAN";

    public static final String GET_ROLES = "/get-roles";
    public static final String CREATE_ROLE = "/create-role";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleController roleController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(roleController).build();
    }

    @Test
    void getRoles_ShouldReturnListOfRoles() throws Exception {
        // Given
        Role role1 = Role.builder()
                .roleId(1L)
                .name(ROLE_ADMIN)
                .build();

        Role role2 = Role.builder()
                .roleId(2L)
                .name(ROLE_CLIENT)
                .build();

        List<Role> roles = Arrays.asList(role1, role2);

        when(roleService.getRoles()).thenReturn(roles);

        // When & Then
        mockMvc.perform(get(GET_ROLES)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].roleId").value(1))
                .andExpect(jsonPath("$[0].name").value(ROLE_ADMIN))
                .andExpect(jsonPath("$[1].roleId").value(2))
                .andExpect(jsonPath("$[1].name").value(ROLE_CLIENT));
    }

    @Test
    void createRole_ShouldReturnCreatedRole() throws Exception {
        // Given
        Role inputRole = Role.builder()
                .name(ROLE_LAB_TECHNICIAN)
                .build();

        Role savedRole = Role.builder()
                .roleId(1L)
                .name(ROLE_LAB_TECHNICIAN)
                .build();

        when(roleService.addRole(any(Role.class))).thenReturn(savedRole);

        // When & Then
        mockMvc.perform(post(CREATE_ROLE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputRole)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roleId").value(1))
                .andExpect(jsonPath("$.name").value(ROLE_LAB_TECHNICIAN));
    }
}