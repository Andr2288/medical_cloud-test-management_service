package com.intern_project.test_management_service.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intern_project.test_management_service.models.TestRequest;
import com.intern_project.test_management_service.services.LaboratorianService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LaboratorianControllerTest {

    public static final String IN_PROGRESS = "IN_PROGRESS";
    public static final String CANCELLED = "CANCELLED";
    public static final String TEST_REQUESTS_TEST_REQUEST_ID_AUTO_UPDATE_STATUS = "/test-requests/{testRequestId}/auto-update-status";
    public static final String TEST_REQUESTS_TEST_REQUEST_ID_CANCEL = "/test-requests/{testRequestId}/cancel";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private LaboratorianService laboratorianService;

    @InjectMocks
    private LaboratorianController laboratorianController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(laboratorianController).build();
    }

    @Test
    void autoUpdateTestRequestStatus_ShouldReturnUpdatedTestRequest() throws Exception {
        // Given
        Long testRequestId = 1L;
        TestRequest updatedTestRequest = TestRequest.builder()
                .testRequestId(testRequestId)
                .status(IN_PROGRESS)
                .build();

        when(laboratorianService.updateTestRequestStatusToNextStep(anyLong())).thenReturn(updatedTestRequest);

        // When & Then
        mockMvc.perform(post(TEST_REQUESTS_TEST_REQUEST_ID_AUTO_UPDATE_STATUS, testRequestId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.testRequestId").value(testRequestId))
                .andExpect(jsonPath("$.status").value(IN_PROGRESS));
    }

    @Test
    void cancelTestRequest_ShouldReturnCancelledTestRequest() throws Exception {
        // Given
        Long testRequestId = 1L;
        TestRequest cancelledTestRequest = TestRequest.builder()
                .testRequestId(testRequestId)
                .status(CANCELLED)
                .build();

        when(laboratorianService.cancelTestRequest(anyLong())).thenReturn(cancelledTestRequest);

        // When & Then
        mockMvc.perform(post(TEST_REQUESTS_TEST_REQUEST_ID_CANCEL, testRequestId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.testRequestId").value(testRequestId))
                .andExpect(jsonPath("$.status").value(CANCELLED));
    }
}