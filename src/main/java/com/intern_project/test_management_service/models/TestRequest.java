package com.intern_project.test_management_service.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
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
    private User user;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @Column(name = "completion_date")
    private LocalDateTime completionDate;

    @Column(name = "estimated_completion_time")
    private LocalDateTime estimatedCompletionTime;

    @Column(name = "status")
    private String status;

    @Column(name = "comments")
    private String comments;
}
