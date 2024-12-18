package com.intern_project.test_management_service.controllers;

import com.intern_project.test_management_service.models.TestRequest;
import com.intern_project.test_management_service.services.TestRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestRequestController {

    private final JdbcTemplate jdbcTemplate;
    private final TestRequestService testRequestService;

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

    @DeleteMapping("/deleteTestRequestsAndResetSequence")
    public void deleteAllTestRequests() {

        String sqlTruncate = "TRUNCATE TABLE test_requests RESTART IDENTITY";
        jdbcTemplate.update(sqlTruncate);
    }
}
