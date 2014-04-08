package com.kandidato.service.vacancy;


import com.kandidato.constants.VacancyState;
import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.manager.vacancy.VacancyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

/**
 * RESTful implementation of {@link com.kandidato.service.vacancy.VacancyService}.
 */
@Controller
@RequestMapping("/vacancy")
public class VacancyServiceImpl implements VacancyService {
    private static final Logger log = LoggerFactory.getLogger(VacancyServiceImpl.class);
    @Inject
    private VacancyManager manager;

    @Override
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Vacancy findById(@PathVariable long id) {
        return manager.findById(id);
    }

    @Override
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET, produces = "application/json")
    public void remove(@PathVariable long id) {
        manager.remove(id);
    }

    @Override
    @RequestMapping(value = "/findByState/{state}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public List<Vacancy> findByState(@PathVariable VacancyState state) {
        log.debug("findByState: {}", state);
        return manager.findByState(state);
    }

    @Override
    @RequestMapping(value = "/findByAuthor/{authorId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public List<Vacancy> findByAuthor(@PathVariable long authorId) {
        return manager.findByAuthor(authorId);
    }
}
