package com.intern_project.test_management_service.controllers;

import com.intern_project.test_management_service.models.TestType;
import com.intern_project.test_management_service.services.TestTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestTypeController {

    private final TestTypeService testTypeService;

    @GetMapping("/get-test-types")
    public List<TestType> getTestTypes() {

        return testTypeService.getTestTypes();
    }

    @PostMapping("/create-test-type")
    public TestType createTestType(@RequestBody TestType testType) {

        return testTypeService.addTestType(testType);
    }

    @DeleteMapping("/delete-test-type-by-id")
    public ResponseEntity<Void> deleteTestById(@RequestParam Long id) {
        try {
            testTypeService.deleteTestTypeById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}