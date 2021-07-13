package com.training.managementProject.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDto {

    private int id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String position;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfEmployment;

}
