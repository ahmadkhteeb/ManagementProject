package com.training.managementProject.repository;

import com.training.managementProject.model.Employee;
import com.training.managementProject.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
