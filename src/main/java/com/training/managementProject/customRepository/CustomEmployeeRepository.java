package com.training.managementProject.customRepository;

import com.training.managementProject.model.Project;
import com.training.managementProject.model.Qualification;
import com.training.managementProject.model.Task;

public interface CustomEmployeeRepository {
    void addQualification(Qualification qualification);
    void deleteQualification(Qualification qualification);

    void addProject(Project project);
    void deleteProject(Project project);

    void addTask(Task task);
    void deleteTask(Task task);
}
