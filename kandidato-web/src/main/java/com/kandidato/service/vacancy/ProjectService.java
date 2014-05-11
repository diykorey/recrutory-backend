package com.kandidato.service.vacancy;


import com.kandidato.persistence.entity.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * RESTful implementation of {@link VacancyService}.
 */
@Controller
@RequestMapping("/project")
public class ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);


    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        for (long i = 0; i < 7; i++) {
            Project project = new Project();
            project.setId(i);
            project.setName("name_" + i);
            project.setDescription("description_" + i);
            projects.add(project);
        }
        return projects;
    }


    @RequestMapping(value = "/find/{begin}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    public List<Project> find(@PathVariable("begin") String begin) {
        List<Project> projects = new ArrayList<>();
        for (long i = 2; i < 5; i++) {
            Project project = new Project();
            project.setId(i);
            project.setName("name_" + i);
            project.setDescription("description_" + i);
            projects.add(project);
        }
        return projects;
    }


}
