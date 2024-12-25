package com.intern_project.test_management_service.services;

import com.intern_project.test_management_service.repositories.TestTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestTypeServiceTest {

    @Mock
    private TestTypeRepository testTypeRepository;

    @InjectMocks
    private TestTypeService testTypeService;

    @Test
    void deleteTestTypeById_WhenTestTypeExists_ShouldDeleteSuccessfully() {
        // Given
        Long testTypeId = 1L;
        when(testTypeRepository.existsById(testTypeId)).thenReturn(true);
        doNothing().when(testTypeRepository).deleteById(testTypeId);

        // When & Then
        assertDoesNotThrow(() -> testTypeService.deleteTestTypeById(testTypeId));

        // Verify
        verify(testTypeRepository).existsById(testTypeId);
        verify(testTypeRepository).deleteById(testTypeId);
    }

    @Test
    void deleteTestTypeById_WhenTestTypeDoesNotExist_ShouldThrowException() {
        // Given
        Long testTypeId = 1L;
        when(testTypeRepository.existsById(testTypeId)).thenReturn(false);

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> testTypeService.deleteTestTypeById(testTypeId)
        );

        assertEquals("Test type with id " + testTypeId + " not found", exception.getMessage());

        // Verify
        verify(testTypeRepository).existsById(testTypeId);
        verify(testTypeRepository, never()).deleteById(any());
    }
}