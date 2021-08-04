package com.training.managementProject.service;

import com.training.managementProject.dto.mapper.ObjectMapperUtils;
import com.training.managementProject.dto.model.QualificationDTO;
import com.training.managementProject.model.Qualification;
import com.training.managementProject.repository.QualificationRepository;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QualificationService {

    private final QualificationRepository qualificationRepository;

    @Autowired
    public QualificationService(QualificationRepository qualificationRepository) {
        this.qualificationRepository = qualificationRepository;
    }

    // Basic CRUD Operations implementation

    // Get all
    public List<QualificationDTO> getQualifications(){
        return ObjectMapperUtils.mapAll(qualificationRepository.findAll(), QualificationDTO.class);
    }

    // Get one
    public QualificationDTO getQualification(int id){
        return ObjectMapperUtils.map(qualificationRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Qualification with the id " + id)
        ), QualificationDTO.class);
    }

    // Add
    public void addQualification(QualificationDTO qualification) throws DuplicateMemberException {
        if(qualificationRepository.existsById(qualification.getId()))
            throw new DuplicateMemberException("Qualification with id " + qualification.getId() + " already exists");
        else
            qualificationRepository.save(ObjectMapperUtils.map(qualification, Qualification.class));
    }

    // Update
    public void updateQualification(QualificationDTO qualification){
        qualificationRepository.findById(qualification.getId()).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Qualification with the id " + qualification.getId())
        );
        qualificationRepository.save(ObjectMapperUtils.map(qualification, Qualification.class));
    }

    // Delete
    public void deleteQualification(int id){
        qualificationRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Qualification with the id " + id)
        );
        qualificationRepository.deleteById(id);
    }

}
