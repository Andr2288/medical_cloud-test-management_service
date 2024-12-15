package com.intern_project.test_management_service.controllers;

import com.intern_project.test_management_service.dtos.TestDto;
import com.intern_project.test_management_service.mapper.TestMapper;
import com.intern_project.test_management_service.models.Test;
import com.intern_project.test_management_service.models.TestType;
import com.intern_project.test_management_service.services.TestService;
import com.intern_project.test_management_service.services.TestTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestTypeController {

    private final JdbcTemplate jdbcTemplate;
    private final TestTypeService testTypeService;

    @GetMapping("/getTestTypes")
    public List<TestDto> getTests() {
        String sql = "SELECT * FROM test_types";
        return this.jdbcTemplate.query(sql, (rs, rowNum) -> TestMapper.mapToTestDto(rs));
    }

    @PostMapping("/createTestType")
    public TestType createTest(@RequestBody TestType testType) {

        return testTypeService.addTestType(testType);
    }

}
