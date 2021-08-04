package com.training.managementProject.dto.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusDTO {

    private int id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String description;
    private ProjectDTO project;
    private TaskDTO task;

    @JsonIgnore
    public TaskDTO getTask() {
        return task;
    }

    @JsonProperty
    public StatusDTO setTask(TaskDTO task) {
        this.task = task;
        return this;
    }

    @JsonIgnore
    public ProjectDTO getProject() {
        return project;
    }

    @JsonProperty
    public StatusDTO setProject(ProjectDTO project) {
        this.project = project;
        return this;
    }
}
