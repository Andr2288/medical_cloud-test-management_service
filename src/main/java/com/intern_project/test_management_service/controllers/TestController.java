package com.intern_project.test_management_service.controllers;

import com.intern_project.test_management_service.dtos.TestDto;
import com.intern_project.test_management_service.mapper.TestMapper;
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
    public List<TestDto> getTests() {
        String sql = "SELECT * FROM tests";
        return this.jdbcTemplate.query(sql, (rs, rowNum) -> TestMapper.mapToTestDto(rs));
    }

    @PostMapping("/createTest")
    public Test createTest(@RequestBody TestDto testDto) {
        return testService.addTest(testDto);
    }

    @DeleteMapping("/deleteAllTestTypes")
    public String deleteAllTests() {
        // Видаляємо всі записи з таблиці
        String sqlDelete = "DELETE FROM test_types";
        jdbcTemplate.update(sqlDelete);

        // Скидаємо лічильник AUTO_INCREMENT для стовпця id
        String sqlResetSequence = "ALTER SEQUENCE test_type_id_seq RESTART WITH 1";
        jdbcTemplate.update(sqlResetSequence);

        return "All tests have been deleted successfully, and the ID sequence has been reset.";
    }
}