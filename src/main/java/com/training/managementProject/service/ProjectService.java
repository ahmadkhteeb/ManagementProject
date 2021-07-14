package com.training.managementProject.service;

import com.training.managementProject.dto.mapper.ProjectMapper;
import com.training.managementProject.dto.model.ProjectDto;
import com.training.managementProject.repository.ProjectRepository;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Basic CRUD Operations implementation

    // Get all
    public List<ProjectDto> getProjects(){
        return projectRepository.findAll().stream().map(ProjectMapper::toProjectDto).collect(Collectors.toList());
    }

    // Get one
    public ProjectDto getProject(int id){
        return ProjectMapper.toProjectDto(projectRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Project with the id " + id)
        ));
    }

    // Add
    public void addProject(ProjectDto project) throws DuplicateMemberException {
        if(projectRepository.existsById(project.getId()))
            throw new DuplicateMemberException("Project with id " + project.getId() + " already exists");
        else
            projectRepository.save(ProjectMapper.toProject(project));
    }

    // Update
    public void updateProject(ProjectDto project){
        projectRepository.findById(project.getId()).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Project with the id " + project.getId())
        );
        projectRepository.save(ProjectMapper.toProject(project));
    }

    // Delete
    public void deleteProject(int id){
        projectRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Project with the id " + id)
        );
        projectRepository.deleteById(id);
    }

}
