package com.training.managementProject.dto.mapper;

import com.training.managementProject.dto.model.DepartmentDto;
import com.training.managementProject.model.Department;

public class DepartmentMapper {
    // Map from department to departmentDto
    public static DepartmentDto toDepartmentDto(Department department){
        return new DepartmentDto()
                .setId(department.getId())
                .setName(department.getName())
                .setNumberOfEmployees(department.getNumberOfEmployees());
    }

    // Map from departmentDto to department
    public static Department toDepartment(DepartmentDto departmentDto){
        return new Department()
                .setId(departmentDto.getId())
                .setName(departmentDto.getName())
                .setNumberOfEmployees(departmentDto.getNumberOfEmployees());
    }
}
