package com.intern_project.test_management_service.controllers;

import com.intern_project.test_management_service.models.TestRequest;
import com.intern_project.test_management_service.services.LaboratorianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LaboratorianController {

    private final LaboratorianService laboratorianService;

    @PostMapping("/test-requests/{testRequestId}/auto-update-status")
    public ResponseEntity<?> autoUpdateTestRequestStatus(@PathVariable Long testRequestId) {
            TestRequest updatedTestRequest = laboratorianService.updateTestRequestStatusToNextStep(testRequestId);
            return ResponseEntity.ok(updatedTestRequest);
    }

    @PostMapping("/test-requests/{testRequestId}/cancel")
    public ResponseEntity<?> cancelTestRequest(@PathVariable Long testRequestId) {
            TestRequest cancelledTestRequest = laboratorianService.cancelTestRequest(testRequestId);
            return ResponseEntity.ok(cancelledTestRequest);
        }
}
