package com.kandidato.service;


import com.kandidato.manager.search.ResumeIndexingManager;
import com.kandidato.manager.vacancy.VacancyManager;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;

@Controller
public class PersistenceService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ResumeIndexingManager indexer;

    @Autowired
    private VacancyManager vacancyManager;

    @RequestMapping(value = "/persistence", method = RequestMethod.GET)
    @ResponseBody
    public String start() {
        //indexer.indexResumes();
        return ((SessionFactory)entityManager.getDelegate()).openSession().getStatistics().toString();
    }
}
