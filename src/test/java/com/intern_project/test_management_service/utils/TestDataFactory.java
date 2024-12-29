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

        TestType testType = createTestType();
        testTypeRepository.save(testType);

        Test test = createTest();
        testRepository.save(test);

        Role role = createRole();
        roleRepository.save(role);

        User user = createUser();
        userRepository.save(user);

        TestRequest testRequest = createTestRequest();
        testRequestRepository.save(testRequest);
    }

    public static Role createRole() {
        return Role.builder()
                .roleId(1L)
                .name("ROLE_TEST")
                .build();
    }

    public static User createUser() {
        Role role = createRole();
        return User.builder()
                .userId(1L)
                .username("testUser")
                .password("testPassword")
                .email("test@example.com")
                .phoneNumber("+1234567890")
                .status("Active")
                .roles(List.of(role))
                .deleteDate(null)
                .build();
    }

    public static TestType createTestType() {
        return TestType.builder()
                .testTypeId(1L)
                .name("Blood Test")
                .description("Test to analyze blood")
                .build();
    }

    public static Test createTest() {
        TestType testType = createTestType();
        return Test.builder()
                .testId(1L)
                .name("Complete Blood Count")
                .description("Complete blood count test")
                .price(new BigDecimal("30.00"))
                .status("Active")
                .testType(testType)
                .build();
    }

    public static TestRequest createTestRequest() {
        User user = createUser();
        Test test = createTest();
        return TestRequest.builder()
                .testRequestId(1L)
                .user(user)
                .test(test)
                .requestDate(LocalDateTime.now())
                .completionDate(LocalDateTime.now().plusDays(1))
                .status("Pending")
                .comments("Test requested for routine checkup.")
                .build();
    }
}