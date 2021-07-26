package com.training.managementProject;

import com.training.managementProject.model.Project;
import com.training.managementProject.repository.ProjectRepository;
import com.training.managementProject.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProjectService projectService;

//    @Test
//    public void findByIdTest(){
//        String name = projectService.getProject(2).getName();
//        assertEquals("Web Application", name);
//    }

}
