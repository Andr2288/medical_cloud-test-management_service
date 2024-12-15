package com.intern_project.test_management_service.services;

import com.intern_project.test_management_service.dtos.TestDto;
import com.intern_project.test_management_service.mapper.TestMapper;
import com.intern_project.test_management_service.models.Test;
import com.intern_project.test_management_service.models.TestType;
import com.intern_project.test_management_service.repositories.TestRepository;
import com.intern_project.test_management_service.repositories.TestTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TestTypeService {

    private final TestTypeRepository testTypeRepository;

    public TestType addTestType(TestType testType) {

        return testTypeRepository.save(testType);
    }
}
