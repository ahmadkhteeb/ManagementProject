package com.training.managementProject.controller;

import com.training.managementProject.dto.model.DepartmentDTO;
import com.training.managementProject.dto.model.EmployeeDTO;
import com.training.managementProject.service.DepartmentService;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(path = "/api/v1/departments")
    public List<DepartmentDTO> getDepartments(){
        return departmentService.getDepartments();
    }

    @RequestMapping(path = "/api/v1/departments/{id}")
    public DepartmentDTO getDepartment(@PathVariable int id){
        return departmentService.getDepartment(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/v1/departments")
    public void addDepartment(@RequestBody DepartmentDTO departmentDto) throws DuplicateMemberException {
        departmentService.addDepartment(departmentDto);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/v1/departments")
    public void updateDepartment(@RequestBody DepartmentDTO departmentDto){
        departmentService.updateDepartment(departmentDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/v1/departments/{id}")
    public void deleteDepartment(@PathVariable int id){
        departmentService.deleteDepartment(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/v1/departments/addEmployee")
    public void addEmployee(@RequestBody EmployeeDTO employeeDto){
        departmentService.addEmployee(employeeDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/v1/departments/deleteEmployee")
    public void deleteEmployee(@RequestBody EmployeeDTO employeeDto){
        departmentService.deleteEmployee(employeeDto);
    }
    
}
