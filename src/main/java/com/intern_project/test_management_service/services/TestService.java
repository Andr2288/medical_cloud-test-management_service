package com.intern_project.test_management_service.services;

import com.intern_project.test_management_service.dtos.TestDto;
import com.intern_project.test_management_service.mapper.TestMapper;
import com.intern_project.test_management_service.models.Test;
import com.intern_project.test_management_service.repositories.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TestService {

    private final TestRepository testRepository;

    public Test addTest(TestDto testDto) {

        Test test = TestMapper.mapToTest(testDto);
        return testRepository.save(test);
    }
}
