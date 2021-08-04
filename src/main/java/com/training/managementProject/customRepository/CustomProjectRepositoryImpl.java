package com.training.managementProject.customRepository;

import com.training.managementProject.model.Project;
import com.training.managementProject.model.Status;
import com.training.managementProject.model.Task;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class CustomProjectRepositoryImpl implements CustomProjectRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public void addTask(Task task) {
        int projectId = task.getProject().getId();
        Project projectObject = em.find(Project.class, projectId);

        if(projectObject == null)
            throw new EntityNotFoundException("Cannot find project with id = " + projectId);

        if(task.getId() == 0){
            projectObject.addTask(task);
            task.setProject(projectObject);
            em.persist(task);
        }else{
            Task taskObject = em.find(Task.class, task.getId());
            projectObject.addTask(taskObject);
            taskObject.setProject(projectObject);
        }
    }

    @Override
    public void deleteTask(Task task) {
        int projectId = task.getProject().getId();
        Project projectObject = em.find(Project.class, projectId);
        Task taskObject = em.find(Task.class, task.getId());

        if(projectObject == null)
            throw new EntityNotFoundException("Cannot find project with id = " + projectId);

        if(taskObject == null)
            throw new EntityNotFoundException("Cannot find task with id = " + task.getId());

        projectObject.deleteTask(taskObject);
        taskObject.setProject(null);
    }

    @Override
    public void addStatus(Status status) {
        int projectId = status.getProject().getId();
        Project projectObject = em.find(Project.class, projectId);

        if(projectObject == null)
            throw new EntityNotFoundException("Cannot find project with id = " + projectId);

        if(status.getId() == 0){
            projectObject.addStatus(status);
            status.setProject(projectObject);
            em.persist(status);
        }else{
            Status statusObject = em.find(Status.class, status.getId());
            projectObject.addStatus(statusObject);
            statusObject.setProject(projectObject);
        }
    }

    @Override
    public void deleteStatus(Status status) {
        int projectId = status.getProject().getId();
        Project projectObject = em.find(Project.class, projectId);
        Status statusObject = em.find(Status.class, status.getId());

        if(projectObject == null)
            throw new EntityNotFoundException("Cannot find project with id = " + projectId);

        if(statusObject == null)
            throw new EntityNotFoundException("Cannot find status with id = " + status.getId());

        projectObject.deleteStatus(statusObject);
        statusObject.setProject(null);
    }
}
