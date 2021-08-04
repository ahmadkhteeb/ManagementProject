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
public class EmployeeDTO {

    private int id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String position;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfEmployment;
    private DepartmentDTO department;
    private List<ProjectDTO> projects = new ArrayList<>();
    private List<QualificationDTO> qualifications = new ArrayList<>();
    private List<TaskDTO> tasks = new ArrayList<>();

    @JsonIgnore
    public DepartmentDTO getDepartment() {
        return department;
    }

    @JsonProperty
    public EmployeeDTO setDepartment(DepartmentDTO department){
        this.department = department;
        return this;
    }
}
