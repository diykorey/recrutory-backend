package com.kandidato.service.vacancy;


import com.kandidato.persistence.entity.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public List<Project> find(@RequestParam("query") String query, @RequestParam("limit") int limit, @RequestParam("page") int page) {
        if (page > 1)
            return new ArrayList<>();
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



}
