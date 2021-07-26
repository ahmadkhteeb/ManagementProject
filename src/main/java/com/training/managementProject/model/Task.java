package com.training.managementProject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

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

}
