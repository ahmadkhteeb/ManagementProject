package com.training.managementProject.customRepository;

import com.training.managementProject.model.Employee;
import com.training.managementProject.model.Project;
import com.training.managementProject.model.Qualification;
import com.training.managementProject.model.Task;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional
public class CustomEmployeeRepositoryImpl implements CustomEmployeeRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public void addQualification(Qualification qualification) {

        for(Employee employee : qualification.getEmployees()){

            int employeeId = employee.getId();
            Employee employeeObject = em.find(Employee.class, employeeId);

            if(employeeObject == null)
                throw new EntityNotFoundException("Cannot find employee with id = " + employeeId);

            // If the qualification already exist in database
            if(qualification.getId() != 0){
                Qualification qualificationObject = em.find(Qualification.class, qualification.getId());
                employeeObject.addQualification(qualificationObject);
                qualificationObject.addEmployee(employeeObject);

            }else{// If the qualification is newly inserted
                Qualification qualificationObject = qualification.clone();
                em.persist(qualificationObject);
                employeeObject.addQualification(qualificationObject);
                qualificationObject.addEmployee(employeeObject);
            }
        }
    }

    @Override
    public void deleteQualification(Qualification qualification) {
        for(Employee employee : qualification.getEmployees()){
            int employeeId = employee.getId();
            Employee employeeObject = em.find(Employee.class, employeeId);

            if(employeeObject == null)
                throw new EntityNotFoundException("Cannot find employee with id = " + employeeId);

            Qualification qualificationObject = em.find(Qualification.class, qualification.getId());

            if(qualificationObject == null)
                throw new EntityNotFoundException("Cannot find qualification with id = " + qualification.getId());

            employeeObject.deleteQualification(qualificationObject);
            qualificationObject.deleteEmployee(employeeObject);
        }
    }

    @Override
    public void addProject(Project project) {
        for(Employee employee : project.getEmployees()){
            int employeeId = employee.getId();
            Employee employeeObject = em.find(Employee.class, employeeId);

            if(employeeObject == null)
                throw new EntityNotFoundException("Cannot find employee with id = " + employeeId);

            // If the qualification already exist in database
            if(project.getId() != 0){
                Project projectObject = em.find(Project.class, project.getId());
                if(projectObject == null)
                    throw new EntityNotFoundException("Cannot find Project with id = " + project.getId());

                employeeObject.addProject(projectObject);
                projectObject.addEmployee(employeeObject);
            }else{// If the qualification is newly inserted
                Project projectObject = project.clone();
                em.persist(projectObject);
                employeeObject.addProject(projectObject);
                projectObject.addEmployee(employeeObject);
            }
        }
    }

    @Override
    public void deleteProject(Project project) {
        for(Employee employee : project.getEmployees()){
            int employeeId = employee.getId();
            Employee employeeObject = em.find(Employee.class, employeeId);

            if(employeeObject == null)
                throw new EntityNotFoundException("Cannot find employee with id = " + employeeId);

            Project projectObject = em.find(Project.class, project.getId());

            if(projectObject == null)
                throw new EntityNotFoundException("Cannot find Project with id = " + project.getId());

            employeeObject.deleteProject(projectObject);
            projectObject.deleteEmployee(employeeObject);
        }
    }

    @Override
    public void addTask(Task task) {
        for(Employee employee : task.getEmployees()){
            int employeeId = employee.getId();
            Employee employeeObject = em.find(Employee.class, employeeId);

            if(employeeObject == null)
                throw new EntityNotFoundException("Cannot find employee with id = " + employeeId);

            Task taskObject = em.find(Task.class, task.getId());

            if(taskObject == null)
                throw new EntityNotFoundException("Cannot find Task with id = " + task.getId());

            employeeObject.addTask(taskObject);
            taskObject.addEmployee(employeeObject);
        }
    }

    @Override
    public void deleteTask(Task task) {
        for(Employee employee : task.getEmployees()){
            int employeeId = employee.getId();
            Employee employeeObject = em.find(Employee.class, employeeId);

            if(employeeObject == null)
                throw new EntityNotFoundException("Cannot find employee with id = " + employeeId);

            Task taskObject = em.find(Task.class, task.getId());

            if(taskObject == null)
                throw new EntityNotFoundException("Cannot find Task with id = " + task.getId());

            employeeObject.deleteTask(taskObject);
            taskObject.deleteEmployee(employeeObject);
        }
    }

    @Override
    public List<Project> getProjects(Employee employee) {
        Session session = em.unwrap(Session.class);

        Query query = session.createQuery("FROM Project P JOIN P.employees E WHERE E.id = :employeeId");
        query.setParameter("employeeId", employee.getId());
        return query.getResultList();
    }


}
