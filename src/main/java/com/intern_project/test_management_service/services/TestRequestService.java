package com.intern_project.test_management_service.services;

import com.intern_project.test_management_service.models.TestRequest;
import com.intern_project.test_management_service.repositories.TestRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TestRequestService {

    private final TestRequestRepository testRequestRepository;

    public List<TestRequest> getTestRequests() {

        return testRequestRepository.findAll();
    }

    public TestRequest addTestRequest(TestRequest testRequest) {

        return testRequestRepository.save(testRequest);
    }
}