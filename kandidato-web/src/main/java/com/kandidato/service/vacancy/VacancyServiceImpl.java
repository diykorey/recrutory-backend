package com.kandidato.service.vacancy;


import com.kandidato.constants.VacancyState;
import com.kandidato.exception.ResourceNotFoundException;
import com.kandidato.manager.vacancy.VacancyManager;
import com.kandidato.persistence.entity.Project;
import com.kandidato.persistence.entity.Tag;
import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.service.HttpAwareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;

/**
 * RESTful implementation of {@link com.kandidato.service.vacancy.VacancyService}.
 */
@Controller
@RequestMapping("/vacancy")
public class VacancyServiceImpl extends HttpAwareService {

    private static final Logger log = LoggerFactory.getLogger(VacancyServiceImpl.class);

    @Inject
    private VacancyManager manager;

    //    @Override
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Transactional
    @ResponseStatus(value = HttpStatus.CREATED)
    public Vacancy create(@RequestBody Vacancy vacancy) {
        return manager.create(vacancy);
    }

    //    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public Vacancy findById(@PathVariable("id") long id) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Vacancy vacancy = manager.findById(id);
        if (null == vacancy) {
            throw new ResourceNotFoundException();
        }
        return vacancy;
    }

    //    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public void remove(@PathVariable("id") long id) {
        manager.remove(id);
    }

    //    @Override
    @RequestMapping(value = "/byState/{state}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public List<Vacancy> findByState(@PathVariable VacancyState state) {
        log.debug("findByState: {}", state);
       /* List<Vacancy> vacancies = manager.findByState(state);
        if (vacancies.isEmpty()) {
            throw new ResourceNotFoundException();
        }    */
        log.debug("findByState: {}", state);
        List<Vacancy> vacancies = new ArrayList<>();
        for (long i = 0; i < 10; i++) {
            Vacancy vacancy = new Vacancy();
            vacancy.setId(i);
            vacancy.setHot(i % 2 == 0);
            vacancy.setRequirements("Vacancy Requirements: " + i);
            vacancy.setState((i > 6) ? VacancyState.HOLD : VacancyState.OPEN);
            Set<Tag> tags = new HashSet<>();
            for (long j = 0; j < 7; j++) {
                Tag tag = new Tag();
                tag.setId(j);
                tag.setKeyword(j + "_" + i);
                tags.add(tag);
            }
            vacancy.setTags(tags);
            vacancy.setCreateTime(new Date());
            Project project = new Project();
            project.setCreationTime(new Date());
            project.setId(i);
            project.setName("Project name" + i);
            project.setDescription("Project Description " + i);
            vacancies.add(vacancy);
        }

        return vacancies;
    }

    //    @Override
    @RequestMapping(value = "/byAuthor/{authorId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    @ResponseStatus(value = HttpStatus.OK)
    public List<Vacancy> findByAuthor(@PathVariable long authorId) {
        List<Vacancy> vacancies = manager.findByAuthor(authorId);
        if (vacancies.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return vacancies;
    }
}
