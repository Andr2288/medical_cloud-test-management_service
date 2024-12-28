package com.intern_project.test_management_service.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "test_requests")
public class TestRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_request_id")
    private Long testRequestId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "User is required.")
    private User user;

    @ManyToOne
    @JoinColumn(name = "test_id")
    @NotNull(message = "Test is required.")
    private Test test;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @Column(name = "completion_date")
    private LocalDateTime completionDate;

    @Column(name = "estimated_completion_time")
    private LocalDateTime estimatedCompletionTime;

    @Column(name = "status")
    @NotBlank(message = "Status is required.")
    private String status = "PENDING";

    @Column(name = "comments")
    @Size(max = 500, message = "Comments must not exceed 500 characters.")
    private String comments;
}
