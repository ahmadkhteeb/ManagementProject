package com.training.managementProject.service;

import com.training.managementProject.dto.mapper.EmployeeMapper;
import com.training.managementProject.dto.model.EmployeeDto;
import com.training.managementProject.model.Employee;
import com.training.managementProject.repository.EmployeeRepository;
import javassist.bytecode.DuplicateMemberException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Basic CRUD Operations implementation

    // Get all
    public List<EmployeeDto> getEmployees(){
        return employeeRepository.findAll().stream().map(EmployeeMapper::toEmployeeDto).collect(Collectors.toList());
    }

    // Get one
    public EmployeeDto getEmployee(int id){
        return EmployeeMapper.toEmployeeDto(employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Employee with the id " + id)
        ));
    }

    // Add
    public void addEmployee(EmployeeDto employee) throws DuplicateMemberException {
        if(employeeRepository.existsById(employee.getId()))
            throw new DuplicateMemberException("Employee with id " + employee.getId() + " already exists");
        else
            employeeRepository.save(EmployeeMapper.toEmployee(employee));
    }

    // Update
    public void updateEmployee(EmployeeDto employee){
        employeeRepository.findById(employee.getId()).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Employee with the id " + employee.getId())
        );
        employeeRepository.save(EmployeeMapper.toEmployee(employee));
    }

    // Delete
    public void deleteEmployee(int id){
        employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Employee with the id " + id)
        );
        employeeRepository.deleteById(id);
    }

}
