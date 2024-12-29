package com.intern_project.test_management_service.repositories;

import com.intern_project.test_management_service.models.Role;
import com.intern_project.test_management_service.models.TestRequest;
import com.intern_project.test_management_service.models.TestType;
import com.intern_project.test_management_service.models.User;
import com.intern_project.test_management_service.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TestRequestRepositoryTest {

    @Autowired
    private TestRequestRepository testRequestRepositoryUnderTest;

    @Autowired
    private TestTypeRepository testTypeRepository;
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        Role role = Role.builder()
                .name("ROLE_PATIENT")
                .build();
        role = roleRepository.save(role);

        User user = User.builder()
                .username("test_user")
                .password("password123")
                .email("test_user@example.com")
                .phoneNumber("+1234567890")
                .roles(List.of(role))
                .status("ACTIVE")
                .build();
        user = userRepository.save(user);

        TestType testType = TestType.builder()
                .name("Mock Test Type")
                .description("Description for test type")
                .build();
        testType = testTypeRepository.save(testType);

        com.intern_project.test_management_service.models.Test test = com.intern_project.test_management_service.models.Test.builder()
                .name("Sample Test")
                .description("Sample test description")
                .testType(testType)
                .price(BigDecimal.valueOf(99.99))
                .status("AVAILABLE")
                .estimatedLength(120)
                .build();
        test = testRepository.save(test);

        TestRequest testRequest = TestRequest.builder()
                .user(user)
                .test(test)
                .requestDate(LocalDateTime.now())
                .completionDate(LocalDateTime.now().plusDays(1))
                .status("PENDING")
                .comments("This is a test request.")
                .build();
        testRequestRepositoryUnderTest.save(testRequest);
    }

    @Test
    void shouldFindTestRequestsByUserUserId() {

        // Given
        Long userId = 1L;

        // When
        List<TestRequest> expectedList = testRequestRepositoryUnderTest.findTestRequestsByUserUserId(userId);

        // Then
        assertThat(expectedList).isNotEmpty();
        assertThat(expectedList).hasSize(1);
        assertEquals(expectedList.get(0).getUser().getUserId(), userId);
    }

    @Test
    void itShouldFindTestRequestsByRequestDateBetweenAndUserUserId() {

        // Given
        Long userId = 1L;
        LocalDateTime startDate = LocalDateTime.now().minusHours(1);
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);

        // When
        List<TestRequest> resultList = testRequestRepositoryUnderTest.findTestRequestsByRequestDateBetweenAndUserUserId(
                startDate,
                endDate,
                userId
        );

        // Then
        assertThat(resultList).isNotEmpty();

        assertThat(resultList).hasSize(1);

        assertEquals(resultList.get(0).getUser().getUserId(), userId);

        assertThat(resultList).allMatch(testRequest ->
                testRequest.getRequestDate().isAfter(startDate.minusSeconds(1)) &&
                        testRequest.getRequestDate().isBefore(endDate.plusSeconds(1))
        );
    }
}