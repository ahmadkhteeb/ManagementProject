package com.training.managementProject.customRepository;

import com.training.managementProject.model.Project;
import com.training.managementProject.model.Qualification;
import com.training.managementProject.model.Status;
import com.training.managementProject.model.Task;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class CustomTaskRepositoryImpl implements CustomTaskRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public void addQualification(Qualification qualification) {
        for(Task task : qualification.getTasks()){
            int taskId = task.getId();
            int qualificationId = qualification.getId();
            Task taskObject = em.find(Task.class, taskId);

            if(taskObject == null)
                throw new EntityNotFoundException("Cannot find Task with id " + taskId);

            if(qualificationId == 0){
                Qualification qualificationObject = qualification.clone();
                taskObject.addQualification(qualificationObject);
                qualificationObject.addTask(taskObject);
                em.persist(qualificationObject);
            }else{
                Qualification qualificationObject = em.find(Qualification.class, qualificationId);
                taskObject.addQualification(qualificationObject);
                qualificationObject.addTask(taskObject);
            }
        }
    }

    @Override
    public void deleteQualification(Qualification qualification) {
        for(Task task : qualification.getTasks()){
            int taskId = task.getId();
            int qualificationId = qualification.getId();
            Task taskObject = em.find(Task.class, taskId);
            Qualification qualificationObject = em.find(Qualification.class, qualificationId);

            if(qualificationObject == null)
                throw new EntityNotFoundException("Cannot find Qualification with id " + qualificationId);

            if(taskObject == null)
                throw new EntityNotFoundException("Cannot find Task with id " + taskId);

            qualificationObject.deleteTask(taskObject);
            taskObject.deleteQualification(qualificationObject);
        }
    }

    @Override
    public void addStatus(Status status) {
        int taskId = status.getTask().getId();
        Task taskObject = em.find(Task.class, taskId);

        if(taskObject == null)
            throw new EntityNotFoundException("Cannot find task with id = " + taskId);

        if(status.getId() == 0){
            taskObject.addStatus(status);
            status.setTask(taskObject);
            em.persist(status);
        }else{
            Status statusObject = em.find(Status.class, status.getId());
            taskObject.addStatus(statusObject);
            statusObject.setTask(taskObject);
        }
    }

    @Override
    public void deleteStatus(Status status){
        int taskId = status.getTask().getId();
        Task taskObject = em.find(Task.class, taskId);
        Status statusObject = em.find(Status.class, status.getId());

        if(taskObject == null)
            throw new EntityNotFoundException("Cannot find task with id = " + taskId);

        if(statusObject == null)
            throw new EntityNotFoundException("Cannot find status with id = " + status.getId());

        taskObject.deleteStatus(statusObject);
        statusObject.setTask(null);
    }

    @Override
    public void addSubTask(Task task) {
        int taskId = task.getSuperTask().getId();
        Task parent = em.find(Task.class, taskId);

        if(parent == null)
            throw new EntityNotFoundException("Cannot find task with id " + taskId);

        if(task.getId() == 0){
            parent.addSubTask(task);
            task.setSuperTask(parent);
            em.persist(task);
        }else{
            Task child = em.find(Task.class, task.getId());
            child.setSuperTask(parent);
            parent.addSubTask(child);
        }
    }

    @Override
    public void deleteSubTask(Task task) {
        int taskId = task.getSuperTask().getId();
        Task parent = em.find(Task.class, taskId);
        Task child = em.find(Task.class, task.getId());
        if(parent == null)
            throw new EntityNotFoundException("Cannot find task with id " + taskId);

        parent.deleteSubTask(child);
        child.setSuperTask(null);
    }
}
