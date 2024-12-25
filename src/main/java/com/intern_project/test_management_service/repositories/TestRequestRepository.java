package com.intern_project.test_management_service.repositories;

import com.intern_project.test_management_service.models.TestRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TestRequestRepository extends JpaRepository<TestRequest, Long> {
    List<TestRequest> findTestRequestsByRequestDateBetweenAndUserUserId(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long userId
    );
    List<TestRequest> findTestRequestsByUserUserId(Long userId);
}