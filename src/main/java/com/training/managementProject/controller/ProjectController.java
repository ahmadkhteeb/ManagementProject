package com.training.managementProject.controller;

import com.training.managementProject.dto.model.ProjectDTO;
import com.training.managementProject.dto.model.StatusDTO;
import com.training.managementProject.dto.model.TaskDTO;
import com.training.managementProject.service.ProjectService;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProjectController {


    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(path = "/api/v1/projects")
    public List<ProjectDTO> getProjects(){
        return projectService.getProjects();
    }

    @RequestMapping(path = "/api/v1/projects/{id}")
    public ProjectDTO getProject(@PathVariable int id){
        return projectService.getProject(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/v1/projects")
    public void addProject(@RequestBody ProjectDTO projectDto) throws DuplicateMemberException {
        projectService.addProject(projectDto);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/v1/projects")
    public void updateProject(@RequestBody ProjectDTO projectDto){
        projectService.updateProject(projectDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/v1/projects/{id}")
    public void deleteProject(@PathVariable int id){
        projectService.deleteProject(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/v1/projects/addTask")
    public void addTask(@RequestBody TaskDTO taskDto){
        projectService.addTask(taskDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/v1/projects/deleteTask")
    public void deleteTask(@RequestBody TaskDTO taskDto){
        projectService.deleteTask(taskDto);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/v1/projects/addStatus")
    public void addStatus(@RequestBody StatusDTO statusDTO){
        projectService.addStatus(statusDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/v1/projects/deleteStatus")
    public void deleteStatus(@RequestBody StatusDTO statusDTO){
        projectService.deleteStatus(statusDTO);
    }

}
