package com.training.managementProject.repository;

import com.training.managementProject.customRepository.CustomDepartmentRepository;
import com.training.managementProject.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>, CustomDepartmentRepository {
}
