package com.training.managementProject.customRepository;

import com.training.managementProject.model.Employee;

public interface CustomDepartmentRepository {

    public void addEmployee(Employee employee);
    public void deleteEmployee(Employee employee);

}
