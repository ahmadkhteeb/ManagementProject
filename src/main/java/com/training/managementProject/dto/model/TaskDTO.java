package com.training.managementProject.dto.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDTO {

    private int id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;
    private ProjectDTO project;

    private List<QualificationDTO> qualifications = new ArrayList<>();

    private List<EmployeeDTO> employees = new ArrayList<>();

    private List<StatusDTO> statuses = new ArrayList<>();

    private List<TaskDTO> subTasks = new ArrayList<>();

    private TaskDTO superTask;

    @JsonIgnore
    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    @JsonProperty
    public TaskDTO setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
        return this;
    }

    @JsonIgnore
    public ProjectDTO getProject() {
        return project;
    }

    @JsonProperty
    public TaskDTO setProject(ProjectDTO project) {
        this.project = project;
        return this;
    }

    @JsonIgnore
    public TaskDTO getSuperTask() {
        return superTask;
    }

    @JsonProperty
    public TaskDTO setSuperTask(TaskDTO superTask) {
        this.superTask = superTask;
        return this;
    }
}
