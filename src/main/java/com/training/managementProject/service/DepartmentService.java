package com.training.managementProject.service;

import com.training.managementProject.dto.mapper.ObjectMapperUtils;
import com.training.managementProject.dto.model.DepartmentDTO;
import com.training.managementProject.dto.model.EmployeeDTO;
import com.training.managementProject.model.Department;
import com.training.managementProject.model.Employee;
import com.training.managementProject.repository.DepartmentRepository;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;


    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Basic CRUD Operations implementation

    // Get all
    public List<DepartmentDTO> getDepartments(){
        return ObjectMapperUtils.mapAll(departmentRepository.findAll(), DepartmentDTO.class);
    }

    // Get one
    public DepartmentDTO getDepartment(int id){
        return ObjectMapperUtils.map(departmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Department with the id " + id)
        ), DepartmentDTO.class);
    }

    // Add
    public void addDepartment(DepartmentDTO department) throws DuplicateMemberException {
        System.out.println(department);
        if(departmentRepository.existsById(department.getId()))
            throw new DuplicateMemberException("Department with id " + department.getId() + " already exists");
        else
            departmentRepository.save(ObjectMapperUtils.map(department, Department.class));
    }

    // Update
    public void updateDepartment(DepartmentDTO department){
        departmentRepository.findById(department.getId()).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Department with the id " + department.getId())
        );
        departmentRepository.save(ObjectMapperUtils.map(department, Department.class));
    }

    // Delete
    public void deleteDepartment(int id){
        departmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Department with the id " + id)
        );
        departmentRepository.deleteById(id);
    }

    public void addEmployee(EmployeeDTO employeeDto){
        departmentRepository.addEmployee(ObjectMapperUtils.map(employeeDto, Employee.class));
    }

    public void deleteEmployee(EmployeeDTO employeeDto){
        departmentRepository.deleteEmployee(ObjectMapperUtils.map(employeeDto, Employee.class));
    }

}
