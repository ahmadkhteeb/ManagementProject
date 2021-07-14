package com.training.managementProject.dto.mapper;


import com.training.managementProject.dto.model.ProjectDto;
import com.training.managementProject.model.Project;

public class ProjectMapper {

    // Map from project to projectDto
    public static ProjectDto toProjectDto(Project project){
        return new ProjectDto()
                .setId(project.getId())
                .setName(project.getName())
                .setStartTime(project.getStartTime())
                .setDeadline(project.getDeadline());
    }

    // Map from projectDto to project
    public static Project toProject(ProjectDto projectDto){
        return new Project()
                .setId(projectDto.getId())
                .setName(projectDto.getName())
                .setStartTime(projectDto.getStartTime())
                .setDeadline(projectDto.getDeadline());
    }

}
