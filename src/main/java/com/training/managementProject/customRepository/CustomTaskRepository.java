package com.training.managementProject.customRepository;

import com.training.managementProject.dto.model.QualificationDTO;
import com.training.managementProject.dto.model.StatusDTO;
import com.training.managementProject.dto.model.TaskDTO;
import com.training.managementProject.model.Qualification;
import com.training.managementProject.model.Status;
import com.training.managementProject.model.Task;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface CustomTaskRepository {

    void addQualification(Qualification qualification);

    void deleteQualification(Qualification qualification);

    void addStatus(Status status);

    void deleteStatus(Status status);

    void addSubTask(Task task);

    void deleteSubTask(Task task);
}
