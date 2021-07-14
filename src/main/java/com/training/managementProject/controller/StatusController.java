package com.training.managementProject.controller;

import com.training.managementProject.dto.model.StatusDto;
import com.training.managementProject.service.StatusService;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StatusController {


    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @RequestMapping(path = "/api/v1/statuses")
    public List<StatusDto> getStatuses(){
        return statusService.getStatuses();
    }

    @RequestMapping(path = "/api/v1/statuses/{id}")
    public StatusDto getStatus(@PathVariable int id){
        return statusService.getStatus(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/v1/statuses")
    public void addStatus(@RequestBody StatusDto statusDto) throws DuplicateMemberException {
        statusService.addStatus(statusDto);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/v1/statuses")
    public void updateStatus(@RequestBody StatusDto statusDto){
        statusService.updateStatus(statusDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/v1/statuses/{id}")
    public void deleteStatus(@PathVariable int id){
        statusService.deleteStatus(id);
    }

}
