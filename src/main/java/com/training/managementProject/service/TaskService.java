package com.training.managementProject.service;

import com.training.managementProject.dto.mapper.TaskMapper;
import com.training.managementProject.dto.model.TaskDto;
import com.training.managementProject.repository.TaskRepository;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Basic CRUD Operations implementation

    // Get all
    public List<TaskDto> getTasks(){
        return taskRepository.findAll().stream().map(TaskMapper::toTaskDto).collect(Collectors.toList());
    }

    // Get one
    public TaskDto getTask(int id){
        return TaskMapper.toTaskDto(taskRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Task with the id " + id)
        ));
    }

    // Add
    public void addTask(TaskDto task) throws DuplicateMemberException {
        if(taskRepository.existsById(task.getId()))
            throw new DuplicateMemberException("Task with id " + task.getId() + " already exists");
        else
            taskRepository.save(TaskMapper.toTask(task));
    }

    // Update
    public void updateTask(TaskDto task){
        taskRepository.findById(task.getId()).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Task with the id " + task.getId())
        );
        taskRepository.save(TaskMapper.toTask(task));
    }

    // Delete
    public void deleteTask(int id){
        taskRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Task with the id " + id)
        );
        taskRepository.deleteById(id);
    }

}
