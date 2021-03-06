package com.training.managementProject.service;

import com.training.managementProject.dto.mapper.*;
import com.training.managementProject.dto.model.EmployeeDTO;
import com.training.managementProject.dto.model.ProjectDTO;
import com.training.managementProject.dto.model.QualificationDTO;
import com.training.managementProject.dto.model.TaskDTO;
import com.training.managementProject.model.Employee;
import com.training.managementProject.model.Project;
import com.training.managementProject.model.Qualification;
import com.training.managementProject.model.Task;
import com.training.managementProject.repository.EmployeeRepository;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Basic CRUD Operations implementation

    // Get all
    public List<EmployeeDTO> getEmployees(){
        return ObjectMapperUtils.mapAll(employeeRepository.findAll1(), EmployeeDTO.class);
    }

    // Get one
    public EmployeeDTO getEmployee(int id){
        Employee employee = employeeRepository.findById1(id);
        if(employee == null){
            throw new EntityNotFoundException("Cannot find Employee with the id " + id);
        }
        return ObjectMapperUtils.map(employee, EmployeeDTO.class);
    }

    // Add
    public void addEmployee(EmployeeDTO employee) throws DuplicateMemberException {
        if(employeeRepository.existsById(employee.getId()))
            throw new DuplicateMemberException("Employee with id " + employee.getId() + " already exists");
        else
            employeeRepository.save1(ObjectMapperUtils.map(employee, Employee.class));
    }

    // Update
    public void updateEmployee(EmployeeDTO employee){
        if(employeeRepository.existsById(employee.getId()))
            employeeRepository.save1(ObjectMapperUtils.map(employee, Employee.class));
        else
            throw new EntityNotFoundException("Cannot find Employee with the id " + employee.getId());
    }

    // Delete
    public void deleteEmployee(int id){
        if(employeeRepository.findById1(id) == null)
            throw new EntityNotFoundException("Cannot find Employee with the id " + id);

        employeeRepository.deleteById1(id);
    }

    public void addQualification(QualificationDTO qualificationDto) {
        employeeRepository.addQualification(ObjectMapperUtils.map(qualificationDto, Qualification.class));
    }

    public void deleteQualification(QualificationDTO qualificationDto){
        employeeRepository.deleteQualification(ObjectMapperUtils.map(qualificationDto, Qualification.class));
    }

    public void addProject(ProjectDTO projectDto) {
        employeeRepository.addProject(ObjectMapperUtils.map(projectDto, Project.class));
    }

    public void deleteProject(ProjectDTO projectDto) {
        employeeRepository.deleteProject(ObjectMapperUtils.map(projectDto, Project.class));
    }

    public void addTask(TaskDTO taskDto) {
        employeeRepository.addTask(ObjectMapperUtils.map(taskDto, Task.class));
    }

    public void deleteTask(TaskDTO taskDto) {
        employeeRepository.deleteTask(ObjectMapperUtils.map(taskDto, Task.class));
    }

    public List<ProjectDTO> getProjects(EmployeeDTO employeeDTO){
        return ObjectMapperUtils.mapAll(
                employeeRepository.getProjects(
                        ObjectMapperUtils.map(employeeDTO, Employee.class)
                ), ProjectDTO.class);
    }

}
