package com.training.managementProject.controller;

import com.training.managementProject.dto.model.EmployeeDTO;
import com.training.managementProject.dto.model.ProjectDTO;
import com.training.managementProject.dto.model.QualificationDTO;
import com.training.managementProject.dto.model.TaskDTO;
import com.training.managementProject.service.EmployeeService;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmployeeController {


    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(path = "/api/v1/employees")
    public List<EmployeeDTO> getEmployees(){
        return employeeService.getEmployees();
    }

    @RequestMapping(path = "/api/v1/employees/{id}")
    public EmployeeDTO getEmployee(@PathVariable int id){
        return employeeService.getEmployee(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/v1/employees")
    public void addEmployee(@RequestBody EmployeeDTO employeeDto) throws DuplicateMemberException {
        employeeService.addEmployee(employeeDto);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/v1/employees")
    public void updateEmployee(@RequestBody EmployeeDTO employeeDto){
        employeeService.updateEmployee(employeeDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/v1/employees/{id}")
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/v1/employees/addQualification")
    public void addQualification(@RequestBody QualificationDTO qualificationDto){
        employeeService.addQualification(qualificationDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/v1/employees/deleteQualification")
    public void deleteQualification(@RequestBody QualificationDTO qualificationDto){
        employeeService.deleteQualification(qualificationDto);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/v1/employees/addProject")
    public void addProject(@RequestBody ProjectDTO projectDto){
        employeeService.addProject(projectDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/v1/employees/deleteProject")
    public void deleteProject(@RequestBody ProjectDTO projectDto){
        employeeService.deleteProject(projectDto);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/v1/employees/addTask")
    public void addTask(@RequestBody TaskDTO taskDto){
        employeeService.addTask(taskDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/v1/employees/deleteTask")
    public void deleteTask(@RequestBody TaskDTO taskDto){
        employeeService.deleteTask(taskDto);
    }

}
