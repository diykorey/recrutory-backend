package com.kandidato.service;


import com.kandidato.manager.vacancy.VacancyManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Controller
public class PersistenceService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private VacancyManager vacancyManager;

    @RequestMapping(value = "/persistence", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public String start() {
        //indexer.indexResumes();

        return entityManager.unwrap(Session.class).getStatistics().toString();
    }
}
