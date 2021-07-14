package com.training.managementProject.controller;

import com.training.managementProject.dto.model.QualificationDto;
import com.training.managementProject.service.QualificationService;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class QualificationController {


    private final QualificationService qualificationService;

    @Autowired
    public QualificationController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    @RequestMapping(path = "/api/v1/qualifications")
    public List<QualificationDto> getQualifications(){
        return qualificationService.getQualifications();
    }

    @RequestMapping(path = "/api/v1/qualifications/{id}")
    public QualificationDto getQualification(@PathVariable int id){
        return qualificationService.getQualification(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/v1/qualifications")
    public void addQualification(@RequestBody QualificationDto qualificationDto) throws DuplicateMemberException {
        qualificationService.addQualification(qualificationDto);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/v1/qualifications")
    public void updateQualification(@RequestBody QualificationDto qualificationDto){
        qualificationService.updateQualification(qualificationDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/v1/qualifications/{id}")
    public void deleteQualification(@PathVariable int id){
        qualificationService.deleteQualification(id);
    }

}
