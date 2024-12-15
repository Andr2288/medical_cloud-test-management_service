package com.intern_project.test_management_service.dtos;

import com.intern_project.test_management_service.models.Test;

import java.util.Set;

public class TestTypeDto {
    private String name;
    private String description;
    private Set<TestDto> tests;
}
