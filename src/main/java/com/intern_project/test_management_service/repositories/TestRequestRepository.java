package com.intern_project.test_management_service.repositories;

import com.intern_project.test_management_service.models.TestRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRequestRepository extends JpaRepository<TestRequest, Long> {

}