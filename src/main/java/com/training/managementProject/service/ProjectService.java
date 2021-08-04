package com.training.managementProject.service;

import com.training.managementProject.dto.mapper.ObjectMapperUtils;
import com.training.managementProject.dto.model.ProjectDTO;
import com.training.managementProject.dto.model.StatusDTO;
import com.training.managementProject.dto.model.TaskDTO;
import com.training.managementProject.model.Project;
import com.training.managementProject.model.Status;
import com.training.managementProject.model.Task;
import com.training.managementProject.repository.ProjectRepository;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Basic CRUD Operations implementation

    // Get all
    public List<ProjectDTO> getProjects(){
        return ObjectMapperUtils.mapAll(projectRepository.findAll(), ProjectDTO.class);
    }

    // Get one
    public ProjectDTO getProject(int id){
        return ObjectMapperUtils.map(projectRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Project with the id " + id)
        ), ProjectDTO.class);
    }

    // Add
    public void addProject(ProjectDTO project) throws DuplicateMemberException {
        if(projectRepository.existsById(project.getId()))
            throw new DuplicateMemberException("Project with id " + project.getId() + " already exists");
        else
            projectRepository.save(ObjectMapperUtils.map(project, Project.class));
    }

    // Update
    public void updateProject(ProjectDTO project){
        projectRepository.findById(project.getId()).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Project with the id " + project.getId())
        );
        projectRepository.save(ObjectMapperUtils.map(project, Project.class));
    }

    // Delete
    public void deleteProject(int id){
        projectRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Project with the id " + id)
        );
        projectRepository.deleteById(id);
    }

    public void addTask(TaskDTO task) {
        projectRepository.addTask(ObjectMapperUtils.map(task, Task.class));
    }

    public void deleteTask(TaskDTO task) {
        projectRepository.deleteTask(ObjectMapperUtils.map(task, Task.class));
    }

    public void addStatus(StatusDTO status) {
        projectRepository.addStatus(ObjectMapperUtils.map(status, Status.class));
    }

    public void deleteStatus(StatusDTO status) {
        projectRepository.deleteStatus(ObjectMapperUtils.map(status, Status.class));
    }





}
