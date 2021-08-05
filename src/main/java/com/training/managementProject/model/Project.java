package com.training.managementProject.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
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
@Entity
public class Project implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    // Employee - Project Relation
    @ManyToMany(mappedBy = "projects")
    @ToString.Exclude
    private List<Employee> employees = new ArrayList<>();

    // Project - Status Relation
    @OneToMany(mappedBy = "project")
    private List<Status> statuses = new ArrayList<>();

    // Project - Task Relation
    @OneToMany(mappedBy = "project")
    private List<Task> tasks = new ArrayList<>();

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public void deleteEmployee(Employee employee){
        employees.remove(employee);
    }

    public void addStatus(Status status){
        statuses.add(status);
    }

    public void deleteStatus(Status status){
        statuses.remove(status);
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void deleteTask(Task task){
        tasks.remove(task);
    }

    @Override
    public Project clone(){
        return new Project()
                .setId(this.id)
                .setName(this.name)
                .setDeadline(this.deadline)
                .setStartTime(this.startTime);
    }
}
