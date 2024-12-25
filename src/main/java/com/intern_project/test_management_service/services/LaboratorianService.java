package com.intern_project.test_management_service.services;

import com.intern_project.test_management_service.models.TestRequest;
import com.intern_project.test_management_service.repositories.TestRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LaboratorianService {

    private final TestRequestRepository testRequestRepository;

    public TestRequest updateTestRequestStatusToNextStep(Long testRequestId) {
        TestRequest testRequest = testRequestRepository.findById(testRequestId)
                .orElseThrow(() -> new EntityNotFoundException("TestRequest not found with ID: " + testRequestId));

        String currentStatus = testRequest.getStatus();
        switch (currentStatus) {
            case "PENDING":
                testRequest.setStatus("PROCESSING");
                break;
            case "PROCESSING":
                testRequest.setStatus("COMPLETED");
                break;
            case "COMPLETED":
                throw new IllegalStateException("TestRequest is already COMPLETED");
            default:
                throw new IllegalArgumentException("Invalid current status: " + currentStatus);
        }
        return testRequestRepository.save(testRequest);
    }

    public TestRequest cancelTestRequest(Long testRequestId) {
        TestRequest testRequest = testRequestRepository.findById(testRequestId)
                .orElseThrow(() -> new EntityNotFoundException("TestRequest not found with ID: " + testRequestId));

        if ("COMPLETED".equals(testRequest.getStatus())) {
            throw new IllegalStateException("Cannot cancel a TestRequest that is already COMPLETED.");
        }

        testRequest.setStatus("CANCELLED");
        return testRequestRepository.save(testRequest);
    }
}
