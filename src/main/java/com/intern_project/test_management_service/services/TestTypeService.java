package com.intern_project.test_management_service.services;

import com.intern_project.test_management_service.models.TestType;
import com.intern_project.test_management_service.repositories.TestTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TestTypeService {

    private final TestTypeRepository testTypeRepository;

    public TestType addTestType(TestType testType) {

        return testTypeRepository.save(testType);
    }

    public List<TestType> getTestTypes() {

        return testTypeRepository.findAll();
    }

    public void deleteTestTypeById(Long id) {
        if (!testTypeRepository.existsById(id)) {
            throw new IllegalArgumentException("Test type with id " + id + " not found");
        }
        testTypeRepository.deleteById(id);
    }
}