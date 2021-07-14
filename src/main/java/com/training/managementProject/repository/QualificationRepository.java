package com.training.managementProject.repository;

import com.training.managementProject.model.Employee;
import com.training.managementProject.model.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Integer> {

}
