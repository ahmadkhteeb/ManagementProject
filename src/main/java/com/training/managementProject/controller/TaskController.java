package com.training.managementProject.controller;

import com.training.managementProject.dto.model.TaskDto;
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
    public List<TaskDto> getTasks(){
        return taskService.getTasks();
    }

    @RequestMapping(path = "/api/v1/tasks/{id}")
    public TaskDto getTask(@PathVariable int id){
        return taskService.getTask(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/v1/tasks")
    public void addTask(@RequestBody TaskDto taskDto) throws DuplicateMemberException {
        taskService.addTask(taskDto);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/v1/tasks")
    public void updateTask(@RequestBody TaskDto taskDto){
        taskService.updateTask(taskDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/v1/tasks/{id}")
    public void deleteTask(@PathVariable int id){
        taskService.deleteTask(id);
    }

}
