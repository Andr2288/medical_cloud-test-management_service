package com.intern_project.test_management_service.repositories;

import com.intern_project.test_management_service.models.TestRequest;

import static org.assertj.core.api.Assertions.assertThat;

import com.intern_project.test_management_service.utils.JsonUtils;
import com.intern_project.test_management_service.utils.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
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

        // Print JSON for debugging
        System.out.println("\nTest Requests JSON:");
        System.out.println(JsonUtils.toFormattedJson(expectedList));
    }
}