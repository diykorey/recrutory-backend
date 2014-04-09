package com.kandidato.service.vacancy;


import com.kandidato.constants.VacancyState;
import com.kandidato.manager.vacancy.VacancyManager;
import com.kandidato.persistence.entity.Vacancy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * RESTful implementation of {@link com.kandidato.service.vacancy.VacancyService}.
 */
@Controller
@RequestMapping("/vacancy")
//FIXME when adding 'implements VacancyService' - service becomes unavailable, probably because of issues with proxies.
public class VacancyServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(VacancyServiceImpl.class);
    @Inject
    private VacancyManager manager;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json" , produces = "application/json")
    @ResponseBody
    @Transactional
    public Vacancy create(@RequestBody Vacancy vacancy) {
        return manager.create(vacancy);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    public Vacancy findById(@PathVariable long id) {
        return manager.findById(id);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET, produces = "application/json")
    @Transactional
    public void remove(@PathVariable long id) {
        manager.remove(id);
    }

    @RequestMapping(value = "/findByState/{state}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    public List<Vacancy> findByState(@PathVariable VacancyState state) {
        log.debug("findByState: {}", state);
        return manager.findByState(state);
    }

    @RequestMapping(value = "/findByAuthor/{authorId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @Transactional
    public List<Vacancy> findByAuthor(@PathVariable long authorId) {
        return manager.findByAuthor(authorId);
    }
}
