package com.intern_project.test_management_service.controllers;

import com.intern_project.test_management_service.models.Test;
import com.intern_project.test_management_service.models.TestRequest;
import com.intern_project.test_management_service.services.TestRequestService;
import com.intern_project.test_management_service.services.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final TestService testService;
    private final TestRequestService testRequestService;


    @GetMapping("/get-tests")
    public List<Test> getTests() {

        return testService.getTests();
    }

    @PostMapping("/create-test")
    public Test createTest(@RequestBody Test test) {

        return testService.addTest(test);
    }

    @GetMapping("/get-test-requests")
    public List<TestRequest> getTestRequests() {

        return testRequestService.getTestRequests();
    }

    @GetMapping("/get-tests-requests-id")
    public List<TestRequest> getTestsId(@RequestBody Long userId) {

        return testRequestService.getTests(userId);
    }

    @PostMapping("/create-test-request")
    public TestRequest createTestRequest(@RequestBody TestRequest testRequest) {

        return testRequestService.addTestRequest(testRequest);
    }
}