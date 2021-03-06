package com.training.managementProject.repository;

import com.training.managementProject.model.Employee;
import com.training.managementProject.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

}
