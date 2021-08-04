package com.training.managementProject.controller;

import com.training.managementProject.dto.model.QualificationDTO;
import com.training.managementProject.dto.model.StatusDTO;
import com.training.managementProject.dto.model.TaskDTO;
import com.training.managementProject.service.TaskService;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TaskController {


    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(path = "/api/v1/tasks")
    public List<TaskDTO> getTasks(){
        return taskService.getTasks();
    }

    @RequestMapping(path = "/api/v1/tasks/{id}")
    public TaskDTO getTask(@PathVariable int id){
        return taskService.getTask(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/v1/tasks")
    public void addTask(@RequestBody TaskDTO taskDto) throws DuplicateMemberException {
        taskService.addTask(taskDto);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/v1/tasks")
    public void updateTask(@RequestBody TaskDTO taskDto){
        taskService.updateTask(taskDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/v1/tasks/{id}")
    public void deleteTask(@PathVariable int id){
        taskService.deleteTask(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/v1/tasks/addQualification")
    public void addQualification(@RequestBody QualificationDTO qualificationDTO){
        taskService.addQualification(qualificationDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/v1/tasks/deleteQualification")
    public void deleteQualification(@RequestBody QualificationDTO qualificationDTO){
        taskService.deleteQualification(qualificationDTO);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/v1/tasks/addStatus")
    public void addStatus(@RequestBody StatusDTO statusDTO){
        taskService.addStatus(statusDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/v1/tasks/deleteStatus")
    public void deleteStatus(@RequestBody StatusDTO statusDTO){
        taskService.deleteStatus(statusDTO);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/v1/tasks/addSubTask")
    public void addSubTask(@RequestBody TaskDTO taskDTO){
        taskService.addSubTask(taskDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/v1/tasks/deleteSubTask")
    public void deleteSubTask(@RequestBody TaskDTO taskDTO){
        taskService.deleteSubTask(taskDTO);
    }

}
