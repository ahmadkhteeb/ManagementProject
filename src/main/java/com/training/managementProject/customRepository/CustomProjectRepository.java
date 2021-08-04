package com.training.managementProject.customRepository;

import com.training.managementProject.model.Status;
import com.training.managementProject.model.Task;

public interface CustomProjectRepository {

    void addTask(Task task);
    void deleteTask(Task task);

    void addStatus(Status status);
    void deleteStatus(Status status);

}
