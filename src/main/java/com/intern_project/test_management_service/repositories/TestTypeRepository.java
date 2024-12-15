package com.intern_project.test_management_service.repositories;

import com.intern_project.test_management_service.models.Test;
import com.intern_project.test_management_service.models.TestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestTypeRepository  extends JpaRepository<TestType, Long> {
}
