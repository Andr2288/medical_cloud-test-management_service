package com.intern_project.test_management_service.controllers;

import com.intern_project.test_management_service.models.TestType;
import com.intern_project.test_management_service.services.TestTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestTypeController {

    private final JdbcTemplate jdbcTemplate;
    private final TestTypeService testTypeService;

    @GetMapping("/getTestTypes")
    public List<TestType> getTestTypes() {

        return testTypeService.getTestTypes();
    }

    @PostMapping("/createTestType")
    public TestType createTestType(@RequestBody TestType testType) {

        return testTypeService.addTestType(testType);
    }

    @DeleteMapping("/deleteTestTypesAndResetSequence")
    public void deleteAllTestTypes() {

        String sqlTruncate = "TRUNCATE TABLE test_types RESTART IDENTITY CASCADE";
        jdbcTemplate.update(sqlTruncate);
    }
}
