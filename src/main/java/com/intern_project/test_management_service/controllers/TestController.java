package com.intern_project.test_management_service.controllers;

import com.intern_project.test_management_service.models.Test;
import com.intern_project.test_management_service.models.TestRequest;
import com.intern_project.test_management_service.services.TestRequestService;
import com.intern_project.test_management_service.services.TestService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping("/get-tests-requests-by-user-id/{userId}")
    public List<TestRequest> getTestRequestsByUserId(@PathVariable Long userId) {

        return testRequestService.getTestRequestsByUserId(userId);
    }

    @PostMapping("/create-test-request")
    public TestRequest createTestRequest(@RequestBody TestRequest testRequest) {

        testRequest.setRequestDate(LocalDateTime.now());
        testRequestService.calculateEstimatedCompletionTime(testRequest);
        return testRequestService.addTestRequest(testRequest);
    }

    @GetMapping("/user/{userId}/date-range")
    public ResponseEntity<List<TestRequest>> getTestRequestsByDateRangeAndUserId(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        try {
            List<TestRequest> testRequests = testRequestService.getTestRequestsByDateRangeAndUserId(
                    startDate,
                    endDate,
                    userId
            );
            return ResponseEntity.ok(testRequests);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/users/{userId}/overdue-tests")
    public ResponseEntity<List<TestRequest>> getOverdueTests(@PathVariable Long userId) {
        try {
            List<TestRequest> overdueTests = testRequestService.findAndUpdateOverdueTests(userId);
            return ResponseEntity.ok(overdueTests);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}