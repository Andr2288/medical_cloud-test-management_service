package com.intern_project.test_management_service.repositories;

import com.intern_project.test_management_service.models.TestRequest;

import static org.assertj.core.api.Assertions.assertThat;

import com.intern_project.test_management_service.utils.JsonUtils;
import com.intern_project.test_management_service.utils.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TestRequestRepositoryTest {

    // Main Repository
    @Autowired
    private TestRequestRepository testRequestRepositoryUnderTest;

    // Addition Repositories
    @Autowired
    private TestTypeRepository testTypeRepository;
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void itShouldFindTestRequestsByUserUserId() {

        // Given
        Long userId = 1L;
        TestDataFactory.createTestData(
                testTypeRepository,
                testRepository,
                roleRepository,
                userRepository,
                testRequestRepositoryUnderTest);

        // When
        List<TestRequest> expectedList = testRequestRepositoryUnderTest.findTestRequestsByUserUserId(userId);

        // Then
        assertThat(expectedList).isNotEmpty();
        assertThat(expectedList).hasSize(1);
        assertEquals(expectedList.get(0).getUser().getUserId(), userId);

        // Show Data for Debugging
        System.out.println(JsonUtils.toFormattedJson(expectedList));
    }

    @Test
    void itShouldFindTestRequestsByRequestDateBetweenAndUserUserId() {

        // Given
        Long userId = 1L;
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(2);

        // Створення тестових даних
        TestDataFactory.createTestData(
                testTypeRepository,
                testRepository,
                roleRepository,
                userRepository,
                testRequestRepositoryUnderTest
        );

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
                        testRequest.getRequestDate().isBefore(endDate.plusSeconds(1)) &&
                        testRequest.getUser().getUserId().equals(userId)
        );

        // Show Data for Debugging
        System.out.println(JsonUtils.toFormattedJson(resultList));
    }
}