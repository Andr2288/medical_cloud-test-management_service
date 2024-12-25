package com.intern_project.test_management_service.services;

import com.intern_project.test_management_service.models.TestRequest;
import com.intern_project.test_management_service.repositories.TestRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LaboratorianServiceTest {

    @Mock
    private TestRequestRepository testRequestRepository;

    @InjectMocks
    private LaboratorianService laboratorianService;

    @Test
    void updateTestRequestStatusToNextStep_WhenStatusIsPending_ShouldUpdateToProcessing() {
        // Given
        Long testRequestId = 1L;
        TestRequest testRequest = new TestRequest();
        testRequest.setTestRequestId(testRequestId);
        testRequest.setStatus("PENDING");

        TestRequest updatedTestRequest = new TestRequest();
        updatedTestRequest.setTestRequestId(testRequestId);
        updatedTestRequest.setStatus("PROCESSING");

        when(testRequestRepository.findById(testRequestId)).thenReturn(Optional.of(testRequest));
        when(testRequestRepository.save(any(TestRequest.class))).thenReturn(updatedTestRequest);

        // When
        TestRequest result = laboratorianService.updateTestRequestStatusToNextStep(testRequestId);

        // Then
        assertEquals("PROCESSING", result.getStatus());
        verify(testRequestRepository).findById(testRequestId);
        verify(testRequestRepository).save(testRequest);
    }

    @Test
    void updateTestRequestStatusToNextStep_WhenStatusIsProcessing_ShouldUpdateToCompleted() {
        // Given
        Long testRequestId = 1L;
        TestRequest testRequest = new TestRequest();
        testRequest.setTestRequestId(testRequestId);
        testRequest.setStatus("PROCESSING");

        TestRequest updatedTestRequest = new TestRequest();
        updatedTestRequest.setTestRequestId(testRequestId);
        updatedTestRequest.setStatus("COMPLETED");

        when(testRequestRepository.findById(testRequestId)).thenReturn(Optional.of(testRequest));
        when(testRequestRepository.save(any(TestRequest.class))).thenReturn(updatedTestRequest);

        // When
        TestRequest result = laboratorianService.updateTestRequestStatusToNextStep(testRequestId);

        // Then
        assertEquals("COMPLETED", result.getStatus());
        verify(testRequestRepository).findById(testRequestId);
        verify(testRequestRepository).save(testRequest);
    }

    @Test
    void updateTestRequestStatusToNextStep_WhenStatusIsCompleted_ShouldThrowException() {
        // Given
        Long testRequestId = 1L;
        TestRequest testRequest = new TestRequest();
        testRequest.setTestRequestId(testRequestId);
        testRequest.setStatus("COMPLETED");

        when(testRequestRepository.findById(testRequestId)).thenReturn(Optional.of(testRequest));

        // When & Then
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> laboratorianService.updateTestRequestStatusToNextStep(testRequestId)
        );

        assertEquals("TestRequest is already COMPLETED", exception.getMessage());
        verify(testRequestRepository).findById(testRequestId);
        verify(testRequestRepository, never()).save(any());
    }

    @Test
    void updateTestRequestStatusToNextStep_WhenInvalidStatus_ShouldThrowException() {
        // Given
        Long testRequestId = 1L;
        TestRequest testRequest = new TestRequest();
        testRequest.setTestRequestId(testRequestId);
        testRequest.setStatus("INVALID_STATUS");

        when(testRequestRepository.findById(testRequestId)).thenReturn(Optional.of(testRequest));

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> laboratorianService.updateTestRequestStatusToNextStep(testRequestId)
        );

        assertEquals("Invalid current status: INVALID_STATUS", exception.getMessage());
        verify(testRequestRepository).findById(testRequestId);
        verify(testRequestRepository, never()).save(any());
    }

    @Test
    void updateTestRequestStatusToNextStep_WhenTestRequestNotFound_ShouldThrowException() {
        // Given
        Long testRequestId = 1L;
        when(testRequestRepository.findById(testRequestId)).thenReturn(Optional.empty());

        // When & Then
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> laboratorianService.updateTestRequestStatusToNextStep(testRequestId)
        );

        assertEquals("TestRequest not found with ID: " + testRequestId, exception.getMessage());
        verify(testRequestRepository).findById(testRequestId);
        verify(testRequestRepository, never()).save(any());
    }

    @Test
    void cancelTestRequest_WhenStatusIsPending_ShouldCancel() {
        // Given
        Long testRequestId = 1L;
        TestRequest testRequest = new TestRequest();
        testRequest.setTestRequestId(testRequestId);
        testRequest.setStatus("PENDING");

        TestRequest cancelledTestRequest = new TestRequest();
        cancelledTestRequest.setTestRequestId(testRequestId);
        cancelledTestRequest.setStatus("CANCELLED");

        when(testRequestRepository.findById(testRequestId)).thenReturn(Optional.of(testRequest));
        when(testRequestRepository.save(any(TestRequest.class))).thenReturn(cancelledTestRequest);

        // When
        TestRequest result = laboratorianService.cancelTestRequest(testRequestId);

        // Then
        assertEquals("CANCELLED", result.getStatus());
        verify(testRequestRepository).findById(testRequestId);
        verify(testRequestRepository).save(testRequest);
    }

    @Test
    void cancelTestRequest_WhenStatusIsCompleted_ShouldThrowException() {
        // Given
        Long testRequestId = 1L;
        TestRequest testRequest = new TestRequest();
        testRequest.setTestRequestId(testRequestId);
        testRequest.setStatus("COMPLETED");

        when(testRequestRepository.findById(testRequestId)).thenReturn(Optional.of(testRequest));

        // When & Then
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> laboratorianService.cancelTestRequest(testRequestId)
        );

        assertEquals("Cannot cancel a TestRequest that is already COMPLETED.", exception.getMessage());
        verify(testRequestRepository).findById(testRequestId);
        verify(testRequestRepository, never()).save(any());
    }

    @Test
    void cancelTestRequest_WhenTestRequestNotFound_ShouldThrowException() {
        // Given
        Long testRequestId = 1L;
        when(testRequestRepository.findById(testRequestId)).thenReturn(Optional.empty());

        // When & Then
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> laboratorianService.cancelTestRequest(testRequestId)
        );

        assertEquals("TestRequest not found with ID: " + testRequestId, exception.getMessage());
        verify(testRequestRepository).findById(testRequestId);
        verify(testRequestRepository, never()).save(any());
    }
}