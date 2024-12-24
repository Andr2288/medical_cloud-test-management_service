package com.intern_project.test_management_service.services;

import com.intern_project.test_management_service.models.TestRequest;
import com.intern_project.test_management_service.repositories.TestRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityNotFoundException;


@Service
@RequiredArgsConstructor
public class TestRequestService {

    private final TestRequestRepository testRequestRepository;

    public List<TestRequest> getTestRequests() {

        return testRequestRepository.findAll();
    }

    public TestRequest addTestRequest(TestRequest testRequest) {

        return testRequestRepository.save(testRequest);
    }

    public List<TestRequest> getTestRequestsByUserId(Long userId) {
        return testRequestRepository.findTestRequestsByUserUserId(userId);
    }

    public void calculateEstimatedCompletionTime(TestRequest testRequest) {
        if (testRequest.getTest() != null && testRequest.getTest().getEstimatedLength() != null) {
            int estimatedLength = testRequest.getTest().getEstimatedLength();
            testRequest.setEstimatedCompletionTime(testRequest.getRequestDate().plusDays(estimatedLength));
        } else {
            throw new IllegalArgumentException("Test or estimated length is missing");
        }
    }

    public List<TestRequest> getTestRequestsByDateRangeAndUserId(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long userId) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date must not be null");
        }

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        return testRequestRepository.findByRequestDateBetweenAndUserUserId(startDate, endDate, userId);
    }

    public boolean updateStatusIfOverdue(TestRequest testRequest) {
        boolean isOverdue = isTestOverdue(testRequest);
        if (isOverdue && (testRequest.getStatus() == null || !"OVERDUE".equals(testRequest.getStatus()))) {
            testRequest.setStatus("OVERDUE");
            testRequestRepository.save(testRequest);
        }
        return isOverdue;
    }

    public boolean isTestOverdue(TestRequest testRequest) {
        return (testRequest.getEstimatedCompletionTime() != null &&
                testRequest.getEstimatedCompletionTime().isBefore(LocalDateTime.now()));
    }

    public List<TestRequest> findAndUpdateOverdueTests(Long userId) {
        List<TestRequest> userTests = testRequestRepository.findAllByUserUserId(userId);
        if (userTests.isEmpty()) {
            throw new EntityNotFoundException("No test requests found for user ID " + userId);
        }

        List<TestRequest> overdueTests = new ArrayList<>();
        for (TestRequest test : userTests) {
            if (updateStatusIfOverdue(test) || test.getStatus().equals("OVERDUE")) {
                overdueTests.add(test);
            }
        }
        return overdueTests;
    }


}