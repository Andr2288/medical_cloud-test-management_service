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

    private final JdbcTemplate jdbcTemplate;
    private final TestService testService;
    private final TestRequestService testRequestService;


    @GetMapping("/getTests")
    public List<Test> getTests() {

        return testService.getTests();
    }

    @PostMapping("/createTest")
    public Test createTest(@RequestBody Test test) {

        return testService.addTest(test);
    }

    @GetMapping("/getTestRequests")
    public List<TestRequest> getTestRequests() {

        return testRequestService.getTestRequests();
    }

    @GetMapping("/getTestsRequestsId")
    public List<TestRequest> getTestsId(@RequestBody Long userId) {

        return testRequestService.getTests(userId);
    }

    @PostMapping("/createTestRequest")
    public TestRequest createTestRequest(@RequestBody TestRequest testRequest) {

        return testRequestService.addTestRequest(testRequest);
    }
}