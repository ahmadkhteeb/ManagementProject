package com.training.managementProject.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QualificationDTO {

    private int id;
    private String name;
    private List<EmployeeDTO> employees = new ArrayList<>();
    private List<TaskDTO> tasks = new ArrayList<>();

    @JsonProperty
    public QualificationDTO setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
        return this;
    }

    @JsonIgnore
    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    @JsonIgnore
    public List<TaskDTO> getTasks() {
        return tasks;
    }

    @JsonProperty
    public QualificationDTO setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
        return this;
    }
}
