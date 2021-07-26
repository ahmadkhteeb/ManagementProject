package com.training.managementProject.service;

import com.training.managementProject.dto.mapper.DepartmentMapper;
import com.training.managementProject.dto.model.DepartmentDto;
import com.training.managementProject.dto.model.DepartmentDto;
import com.training.managementProject.model.Department;
import com.training.managementProject.repository.DepartmentRepository;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Basic CRUD Operations implementation

    // Get all
    public List<DepartmentDto> getDepartments(){
        return departmentRepository.findAll().stream().map(DepartmentMapper::toDepartmentDto).collect(Collectors.toList());
    }

    // Get one
    public DepartmentDto getDepartment(int id){
        return DepartmentMapper.toDepartmentDto(departmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Department with the id " + id)
        ));
    }

    // Add
    public void addDepartment(DepartmentDto department) throws DuplicateMemberException {
        if(departmentRepository.existsById(department.getId()))
            throw new DuplicateMemberException("Department with id " + department.getId() + " already exists");
        else
            departmentRepository.save(DepartmentMapper.toDepartment(department));
    }

    // Update
    public void updateDepartment(DepartmentDto department){
        departmentRepository.findById(department.getId()).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Department with the id " + department.getId())
        );
        departmentRepository.save(DepartmentMapper.toDepartment(department));
    }

    // Delete
    public void deleteDepartment(int id){
        departmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Department with the id " + id)
        );
        departmentRepository.deleteById(id);
    }




}
