package com.intern_project.test_management_service.mapper;

import com.intern_project.test_management_service.dtos.TestDto;
import com.intern_project.test_management_service.models.Test;
import com.intern_project.test_management_service.models.TestType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMapper {

    public static TestDto mapToTestDto(Test test) {
        return new TestDto(
                test.getId(),
                test.getName(),
                test.getDescription(),
                test.getTestType(),
                test.getPrice(),
                test.getStatus()
        );
    }

    public static TestDto mapToTestDto(ResultSet rs) throws SQLException {
            return new TestDto(
                    rs.getLong("test_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getObject("test_type_id", TestType.class),
                    rs.getBigDecimal("price"),
                    rs.getString("status")
            );
    }

    public static Test mapToTest(TestDto testDto) {
        return new Test(
          testDto.getId(),
          testDto.getName(),
          testDto.getDescription(),
          testDto.getType(),
          testDto.getPrice(),
          testDto.getStatus()
        );
    }
}
