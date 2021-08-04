package com.training.managementProject.customRepository;

import com.training.managementProject.model.Department;
import com.training.managementProject.model.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CustomDepartmentRepositoryImpl implements CustomDepartmentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addEmployee(Employee employee) {
        int departmentId = employee.getDepartment().getId();
        Department department = em.find(Department.class, departmentId);

        if(department == null)
            throw new EntityNotFoundException("Cannot find Department with the id " + departmentId);
        // If the user is inserting a new employee
        if (employee.getId() == 0) {
            em.persist(employee);
            department.addEmployee(employee);
            employee.setDepartment(department);
        }else{// If the user inserting an existing employee
            Employee existingEmployee = em.find(Employee.class, employee.getId());
            department.addEmployee(existingEmployee);
        }
    }

    @Override
    public void deleteEmployee(Employee employee) {
        int departmentId = employee.getDepartment().getId();
        Department department = em.find(Department.class, departmentId);

        // Check if department exists
        if(department == null)
            throw new EntityNotFoundException("Cannot find Department with the id " + departmentId);

        Employee employeeObject = em.find(Employee.class, employee.getId());

        // Check if employee exists
        if(employeeObject == null)
            throw new EntityNotFoundException("Cannot find Employee with the id " + employee.getId());
        else {
            department.deleteEmployee(employeeObject);
            employeeObject.setDepartment(null);
        }
    }


}
