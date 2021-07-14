package com.training.managementProject.service;

import com.training.managementProject.dto.mapper.QualificationMapper;
import com.training.managementProject.dto.model.QualificationDto;
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
    public List<QualificationDto> getQualifications(){
        return qualificationRepository.findAll().stream().map(QualificationMapper::toQualificationDto).collect(Collectors.toList());
    }

    // Get one
    public QualificationDto getQualification(int id){
        return QualificationMapper.toQualificationDto(qualificationRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Qualification with the id " + id)
        ));
    }

    // Add
    public void addQualification(QualificationDto qualification) throws DuplicateMemberException {
        if(qualificationRepository.existsById(qualification.getId()))
            throw new DuplicateMemberException("Qualification with id " + qualification.getId() + " already exists");
        else
            qualificationRepository.save(QualificationMapper.toQualification(qualification));
    }

    // Update
    public void updateQualification(QualificationDto qualification){
        qualificationRepository.findById(qualification.getId()).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Qualification with the id " + qualification.getId())
        );
        qualificationRepository.save(QualificationMapper.toQualification(qualification));
    }

    // Delete
    public void deleteQualification(int id){
        qualificationRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find Qualification with the id " + id)
        );
        qualificationRepository.deleteById(id);
    }

}
