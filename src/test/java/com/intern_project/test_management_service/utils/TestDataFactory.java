package com.intern_project.test_management_service.utils;

import com.intern_project.test_management_service.models.*;
import com.intern_project.test_management_service.repositories.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TestDataFactory {

    public static void createAllTestData(
            TestTypeRepository testTypeRepository,
            TestRepository testRepository,
            RoleRepository roleRepository,
            UserRepository userRepository,
            TestRequestRepository testRequestRepository) {

        // Create TestType
        TestType testType = TestType.builder()
                .name("Blood Test")
                .description("Test to analyze blood")
                .build();
        testTypeRepository.save(testType);

        // Create Test
        Test test = Test.builder()
                .name("Complete Blood Count")
                .description("Complete blood count test")
                .price(new BigDecimal("30.00"))
                .status("Active")
                .testType(testType)
                .build();
        testRepository.save(test);

        // Create Role
        Role role = Role.builder()
                .name("ROLE_TEST")
                .build();
        roleRepository.save(role);

        // Create User
        User user = User.builder()
                .username("testUser")
                .password("testPassword")
                .email("test@example.com")
                .phoneNumber("+1234567890")
                .status("Active")
                .roles(List.of(role))
                .build();
        userRepository.save(user);

        // Create TestRequest
        TestRequest testRequest = TestRequest.builder()
                .user(user)
                .test(test)
                .requestDate(LocalDateTime.now())
                .completionDate(LocalDateTime.now().plusDays(1))
                .status("Pending")
                .comments("Test requested for routine checkup.")
                .build();
        testRequestRepository.save(testRequest);
    }

    public static User createUser() {
        // Create Role
        Role role = Role.builder()
                .name("ROLE_TEST")
                .build();

        // Create User
        User user = User.builder()
                .username("testUser")
                .password("testPassword")
                .email("test@example.com")
                .phoneNumber("+1234567890")
                .status("Active")
                .roles(List.of(role))
                .build();

        return user;
    }
}