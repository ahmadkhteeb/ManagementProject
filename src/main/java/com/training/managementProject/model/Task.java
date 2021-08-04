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
public class Task implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    @ManyToMany
    @JoinColumn(name = "qualification_id")
    private List<Qualification> qualifications = new ArrayList<>();

    // Employee - Task Relation
    @ManyToMany(mappedBy = "tasks")
    private List<Employee> employees = new ArrayList<>();

    // Project - Task Relation
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    // Task - Status Relation
    @OneToMany(mappedBy = "task")
    private List<Status> statuses = new ArrayList<>();

    // Task - Task Relation
    @OneToMany(mappedBy = "superTask")
    private List<Task> subTasks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "subtask_id")
    private Task superTask;

    public void addQualification(Qualification qualification){
        qualifications.add(qualification);
    }

    public void deleteQualification(Qualification qualification){
        qualifications.remove(qualification);
    }

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

    public void addSubTask(Task subTask){
        subTasks.add(subTask);
    }

    public void deleteSubTask(Task subTask){
        subTasks.remove(subTask);
    }

    @Override
    protected Task clone() {
        return new Task()
                .setId(this.id)
                .setName(this.name)
                .setStartTime(this.startTime)
                .setDeadline(this.deadline);
    }
}
