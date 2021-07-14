package com.training.managementProject.controller;

import com.training.managementProject.dto.model.ProjectDto;
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
    public List<ProjectDto> getProjects(){
        return projectService.getProjects();
    }

    @RequestMapping(path = "/api/v1/projects/{id}")
    public ProjectDto getProject(@PathVariable int id){
        return projectService.getProject(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/v1/projects")
    public void addProject(@RequestBody ProjectDto projectDto) throws DuplicateMemberException {
        projectService.addProject(projectDto);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/v1/projects")
    public void updateProject(@RequestBody ProjectDto projectDto){
        projectService.updateProject(projectDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/v1/projects/{id}")
    public void deleteProject(@PathVariable int id){
        projectService.deleteProject(id);
    }

}
