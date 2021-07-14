package com.training.managementProject.dto.mapper;


import com.training.managementProject.dto.model.QualificationDto;
import com.training.managementProject.model.Qualification;

public class QualificationMapper {

    // Map from qualification to qualificationDto
    public static QualificationDto toQualificationDto(Qualification qualification){
        return new QualificationDto()
                .setId(qualification.getId())
                .setName(qualification.getName());
    }

    // Map from qualificationDto to qualification
    public static Qualification toQualification(QualificationDto qualificationDto){
        return new Qualification()
                .setId(qualificationDto.getId())
                .setName(qualificationDto.getName());
    }

}
