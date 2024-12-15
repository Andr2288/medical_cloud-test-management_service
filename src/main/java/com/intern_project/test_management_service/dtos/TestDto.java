package com.intern_project.test_management_service.dtos;

import com.intern_project.test_management_service.models.TestType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestDto {

    private Long id;
    private String name;
    private String description;
    private TestType type;
    private BigDecimal price;
    private String status;
}
