package com.intern_project.test_management_service.services;

import com.intern_project.test_management_service.models.Test;
import com.intern_project.test_management_service.repositories.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TestService {

    private final TestRepository testRepository;

    public Test addTest(Test test) {

        return testRepository.save(test);
    }

    public List<Test> getTests() {

        return testRepository.findAll();
    }
}