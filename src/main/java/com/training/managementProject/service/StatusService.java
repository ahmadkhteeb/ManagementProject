package com.training.managementProject.service;

import com.training.managementProject.dto.mapper.ObjectMapperUtils;
import com.training.managementProject.dto.model.StatusDTO;
import com.training.managementProject.model.Status;
import com.training.managementProject.repository.StatusRepository;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    // Basic CRUD Operations implementation

    // Get all
    public List<StatusDTO> getStatuses(){
        return ObjectMapperUtils.mapAll(statusRepository.findAll(), StatusDTO.class);
    }

    // Get one
    public StatusDTO getStatus(int id){
        return ObjectMapperUtils.map(statusRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Status with the id " + id)
        ), StatusDTO.class);
    }

    // Add
    public void addStatus(StatusDTO status) throws DuplicateMemberException {
        if(statusRepository.existsById(status.getId()))
            throw new DuplicateMemberException("Status with id " + status.getId() + " already exists");
        else
            statusRepository.save(ObjectMapperUtils.map(status, Status.class));
    }

    // Update
    public void updateStatus(StatusDTO status){
        statusRepository.findById(status.getId()).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Status with the id " + status.getId())
        );
        statusRepository.save(ObjectMapperUtils.map(status, Status.class));
    }

    // Delete
    public void deleteStatus(int id){
        statusRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Status with the id " + id)
        );
        statusRepository.deleteById(id);
    }

}
