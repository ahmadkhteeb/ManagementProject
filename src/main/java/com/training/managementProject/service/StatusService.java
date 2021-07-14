package com.training.managementProject.service;

import com.training.managementProject.dto.mapper.StatusMapper;
import com.training.managementProject.dto.model.StatusDto;
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
    public List<StatusDto> getStatuses(){
        return statusRepository.findAll().stream().map(StatusMapper::toStatusDto).collect(Collectors.toList());
    }

    // Get one
    public StatusDto getStatus(int id){
        return StatusMapper.toStatusDto(statusRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Status with the id " + id)
        ));
    }

    // Add
    public void addStatus(StatusDto status) throws DuplicateMemberException {
        if(statusRepository.existsById(status.getId()))
            throw new DuplicateMemberException("Status with id " + status.getId() + " already exists");
        else
            statusRepository.save(StatusMapper.toStatus(status));
    }

    // Update
    public void updateStatus(StatusDto status){
        statusRepository.findById(status.getId()).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Status with the id " + status.getId())
        );
        statusRepository.save(StatusMapper.toStatus(status));
    }

    // Delete
    public void deleteStatus(int id){
        statusRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Status with the id " + id)
        );
        statusRepository.deleteById(id);
    }

}
