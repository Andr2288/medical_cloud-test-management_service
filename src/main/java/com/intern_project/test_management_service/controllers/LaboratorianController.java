package com.intern_project.test_management_service.controllers;

import com.intern_project.test_management_service.models.TestRequest;
import com.intern_project.test_management_service.services.LaboratorianService;
import jakarta.persistence.EntityNotFoundException;
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
        try {
            TestRequest updatedTestRequest = laboratorianService.updateTestRequestStatusToNextStep(testRequestId);
            return ResponseEntity.ok(updatedTestRequest);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("TestRequest with ID " + testRequestId + " not found.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/test-requests/{testRequestId}/cancel")
    public ResponseEntity<?> cancelTestRequest(@PathVariable Long testRequestId) {
        try {
            TestRequest cancelledTestRequest = laboratorianService.cancelTestRequest(testRequestId);
            return ResponseEntity.ok(cancelledTestRequest);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("TestRequest with ID " + testRequestId + " not found.");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
