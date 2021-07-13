package com.training.managementProject.controller;

import com.training.managementProject.dto.model.EmployeeDto;
import com.training.managementProject.model.Employee;
import com.training.managementProject.service.EmployeeService;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(path = "/api/v1/employees")
    public List<EmployeeDto> getEmployees(){
        return employeeService.getEmployees();
    }

    @RequestMapping(path = "/api/v1/employees/{id}")
    public EmployeeDto getEmployee(@PathVariable int id){
        return employeeService.getEmployee(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/v1/employees")
    public void addEmployee(@RequestBody EmployeeDto employeeDto) throws DuplicateMemberException {
        employeeService.addEmployee(employeeDto);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/v1/employees")
    public void updateEmployee(@RequestBody EmployeeDto employeeDto){
        employeeService.updateEmployee(employeeDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/v1/employees/{id}")
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
    }

}
