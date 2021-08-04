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
public class Qualification implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    // Employee - Qualification Relation
    @ManyToMany(mappedBy = "qualifications")
    private List<Employee> employees = new ArrayList<>();

    // Qualification - Task Relation
    @ManyToMany(mappedBy = "qualifications")
    private List<Task> tasks = new ArrayList<>();

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public void deleteEmployee(Employee employee){
        employees.remove(employee);
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void deleteTask(Task task){
        tasks.remove(task);
    }

    @Override
    public Qualification clone(){
        Qualification qualification = new Qualification();
        qualification.setName(this.name);
        return qualification;
    }
}
