package com.training.managementProject.dto.mapper;


import com.training.managementProject.dto.model.EmployeeDto;
import com.training.managementProject.model.Employee;

public class EmployeeMapper {

    // Map from employee to employeeDto
    public static EmployeeDto toEmployeeDto(Employee employee){
        return new EmployeeDto()
                .setId(employee.getId())
                .setName(employee.getName())
                .setDateOfBirth(employee.getDateOfBirth())
                .setDateOfEmployment(employee.getDateOfEmployment())
                .setPosition(employee.getPosition());
    }

    // Map from employeeDto to employee
    public static Employee toEmployee(EmployeeDto employeeDto){
        return new Employee()
                .setId(employeeDto.getId())
                .setName(employeeDto.getName())
                .setDateOfBirth(employeeDto.getDateOfBirth())
                .setDateOfEmployment(employeeDto.getDateOfEmployment())
                .setPosition(employeeDto.getPosition());
    }

}
