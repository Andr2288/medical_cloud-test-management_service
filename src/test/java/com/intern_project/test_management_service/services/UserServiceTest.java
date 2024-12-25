package com.intern_project.test_management_service.services;

import com.intern_project.test_management_service.models.Role;
import com.intern_project.test_management_service.models.User;
import com.intern_project.test_management_service.repositories.UserRepository;
import com.intern_project.test_management_service.utils.JsonUtils;
import com.intern_project.test_management_service.utils.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserById() {
        
        // Given
        Long userId = 1L;
        User mockUser = TestDataFactory.createUser();
        mockUser.setUserId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        // When
        User resultUser = userService.getUserById(1L);

        // Then
        assertNotNull(resultUser, "The returned user should not be null");
        assertEquals("test@example.com", resultUser.getEmail(), "The user's email should match the expected value");
        assertEquals("testUser", resultUser.getUsername(), "The username should match the expected value");

        verify(userRepository, times(1)).findById(userId);
    }
}