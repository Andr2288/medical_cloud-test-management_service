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
        TestType testType = createTestType();
        testTypeRepository.save(testType);

        // Create Test
        Test test = createTest();
        testRepository.save(test);

        // Create Role
        Role role = createRole();
        roleRepository.save(role);

        // Create User
        User user = createUser();
        userRepository.save(user);

        // Create TestRequest
        TestRequest testRequest = createTestRequest();
        testRequestRepository.save(testRequest);
    }

    public static Role createRole() {
        // Create Role
        return Role.builder()
                .name("ROLE_TEST")
                .build();
    }

    public static User createUser() {
        // Create Role
        Role role = createRole();

        // Create User
        return User.builder()
                .username("testUser")
                .password("testPassword")
                .email("test@example.com")
                .phoneNumber("+1234567890")
                .status("Active")
                .roles(List.of(role))
                .build();
    }

    public static TestType createTestType() {
        // Create TestType
        return TestType.builder()
                .name("Blood Test")
                .description("Test to analyze blood")
                .build();
    }

    public static Test createTest() {
        // Create TestType
        TestType testType = createTestType();

        // Create Test
        return Test.builder()
                .name("Complete Blood Count")
                .description("Complete blood count test")
                .price(new BigDecimal("30.00"))
                .status("Active")
                .testType(testType)
                .build();
    }

    public static TestRequest createTestRequest() {
        // Create User
        User user = createUser();

        // Create Test
        Test test = createTest();

        // Create TestRequest
        return TestRequest.builder()
                .user(user)
                .test(test)
                .requestDate(LocalDateTime.now())
                .completionDate(LocalDateTime.now().plusDays(1))
                .status("Pending")
                .comments("Test requested for routine checkup.")
                .build();
    }
}