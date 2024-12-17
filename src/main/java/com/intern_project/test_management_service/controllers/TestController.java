package com.intern_project.test_management_service.controllers;

import com.intern_project.test_management_service.models.Test;
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

    @GetMapping("/getTests")
    public List<Test> getTests() {

        return testService.getTests();
    }

    @PostMapping("/createTest")
    public Test createTest(@RequestBody Test test) {

        return testService.addTest(test);
    }

    @DeleteMapping("/deleteTestsAndResetSequence")
    public void deleteAllTests() {

        String sqlTruncate = "TRUNCATE TABLE tests RESTART IDENTITY";
        jdbcTemplate.update(sqlTruncate);
    }
}