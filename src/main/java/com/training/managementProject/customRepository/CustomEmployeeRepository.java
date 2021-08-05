package com.training.managementProject.customRepository;

import com.training.managementProject.model.Employee;
import com.training.managementProject.model.Project;
import com.training.managementProject.model.Qualification;
import com.training.managementProject.model.Task;

import java.util.List;

public interface CustomEmployeeRepository {
    void addQualification(Qualification qualification);
    void deleteQualification(Qualification qualification);

    void addProject(Project project);
    void deleteProject(Project project);

    void addTask(Task task);
    void deleteTask(Task task);

    List<Project> getProjects(Employee employee);
}
