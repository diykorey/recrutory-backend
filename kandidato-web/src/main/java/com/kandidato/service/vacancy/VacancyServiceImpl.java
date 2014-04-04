package com.kandidato.service.vacancy;


import com.kandidato.constants.VacancyState;
import com.kandidato.entity.Vacancy;
import com.kandidato.manager.vacancy.VacancyManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * RESTful implementation of {@link com.kandidato.service.vacancy.VacancyService}.
 */
@Controller
public class VacancyServiceImpl implements VacancyService {

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
    @ResponseBody
    public Vacancy remove(@PathVariable long id) {
        return manager.remove(id);
    }

    @Override
    @RequestMapping(value = "/findByState", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public List<Vacancy> findByState(@RequestBody VacancyState state) {
        return manager.findByState(state);
    }

    @Override
    @RequestMapping(value = "/findByAuthor/{authorId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public List<Vacancy> findByAuthor(@PathVariable long authorId) {
        return manager.findByAuthor(authorId);
    }
}
