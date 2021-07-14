package com.training.managementProject.dto.mapper;


import com.training.managementProject.dto.model.StatusDto;
import com.training.managementProject.model.Status;

public class StatusMapper {

    // Map from status to statusDto
    public static StatusDto toStatusDto(Status status){
        return new StatusDto()
                .setId(status.getId())
                .setName(status.getName())
                .setDate(status.getDate())
                .setDescription(status.getDescription());
    }

    // Map from statusDto to status
    public static Status toStatus(StatusDto statusDto){
        return new Status()
                .setId(statusDto.getId())
                .setName(statusDto.getName())
                .setDate(statusDto.getDate())
                .setDescription(statusDto.getDescription());
    }

}
