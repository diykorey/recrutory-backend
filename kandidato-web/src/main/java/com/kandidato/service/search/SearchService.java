package com.kandidato.service.search;

import com.kandidato.exception.ResourceNotFoundException;
import com.kandidato.manager.search.PersonSearchManager;
import com.kandidato.persistence.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchService {

    @Autowired
    private PersonSearchManager personSearchManager;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    public List<Person> findByVacancyId(@RequestParam(value = "vacancyId", required = false) Long vacancyId,//
                                        @RequestParam(value = "requirements", required = false) String requirements) {

        List<Person> persons = null;
        if(vacancyId != null){
            persons = personSearchManager.findPersonByVacancyId(vacancyId);
        } else if (requirements != null){
            persons = personSearchManager.findPersonByRequirements(requirements);
        }
        if (null == persons || persons.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return persons;
    }
}
