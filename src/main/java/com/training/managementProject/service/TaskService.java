package com.training.managementProject.service;

import com.training.managementProject.dto.mapper.ObjectMapperUtils;
import com.training.managementProject.dto.model.QualificationDTO;
import com.training.managementProject.dto.model.StatusDTO;
import com.training.managementProject.dto.model.TaskDTO;
import com.training.managementProject.model.Qualification;
import com.training.managementProject.model.Status;
import com.training.managementProject.model.Task;
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
    public List<TaskDTO> getTasks(){
        return ObjectMapperUtils.mapAll(taskRepository.findAll(), TaskDTO.class);
    }

    // Get one
    public TaskDTO getTask(int id){
        return ObjectMapperUtils.map(taskRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Task with the id " + id)
        ), TaskDTO.class);
    }

    // Add
    public void addTask(TaskDTO task) throws DuplicateMemberException {
        if(taskRepository.existsById(task.getId()))
            throw new DuplicateMemberException("Task with id " + task.getId() + " already exists");
        else
            taskRepository.save(ObjectMapperUtils.map(task, Task.class));
    }

    // Update
    public void updateTask(TaskDTO task){
        taskRepository.findById(task.getId()).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Task with the id " + task.getId())
        );
        taskRepository.save(ObjectMapperUtils.map(task, Task.class));
    }

    // Delete
    public void deleteTask(int id){
        taskRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Task with the id " + id)
        );
        taskRepository.deleteById(id);
    }

    public void addQualification(QualificationDTO qualificationDTO) {
        taskRepository.addQualification(ObjectMapperUtils.map(qualificationDTO, Qualification.class));
    }

    public void deleteQualification(QualificationDTO qualificationDTO) {
        taskRepository.deleteQualification(ObjectMapperUtils.map(qualificationDTO, Qualification.class));
    }

    public void addStatus(StatusDTO statusDTO) {
        taskRepository.addStatus(ObjectMapperUtils.map(statusDTO, Status.class));
    }

    public void deleteStatus(StatusDTO statusDTO) {
        taskRepository.deleteStatus(ObjectMapperUtils.map(statusDTO, Status.class));
    }

    public void addSubTask(TaskDTO taskDTO) {
        taskRepository.addSubTask(ObjectMapperUtils.map(taskDTO, Task.class));
    }

    public void deleteSubTask(TaskDTO taskDTO) {
        taskRepository.deleteSubTask(ObjectMapperUtils.map(taskDTO, Task.class));
    }
}
