package com.training.managementProject.dto.mapper;


import com.training.managementProject.dto.model.EmployeeDto;
import com.training.managementProject.model.Employee;

public class EmployeeMapper {

    public static EmployeeDto toEmployeeDto(Employee employee){
        return new EmployeeDto()
                .setName(employee.getName())
                .setDateOfBirth(employee.getDateOfBirth())
                .setDateOfEmployment(employee.getDateOfEmployment())
                .setPosition(employee.getPosition());

    }

}
