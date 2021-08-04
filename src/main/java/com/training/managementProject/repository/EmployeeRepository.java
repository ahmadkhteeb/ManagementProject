package com.training.managementProject.repository;

import com.training.managementProject.customRepository.CustomEmployeeRepository;
import com.training.managementProject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, CustomEmployeeRepository {

}
