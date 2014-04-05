package com.kandidato.service;


import com.kandidato.manager.search.ResumeIndexingManager;
import com.kandidato.repository.resume.ResumeRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
public class PersistenceService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ResumeIndexingManager indexer;

    @RequestMapping(value = "/persistence", method = RequestMethod.GET)
    @ResponseBody
    public String start() {
        //indexer.indexResumes();
        return sessionFactory.openSession().getStatistics().toString();
    }
}
