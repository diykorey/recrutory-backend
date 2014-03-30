package com.kandidato.service;


import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

public class PersistenceService {
    @Inject
    private SessionFactory sessionFactory;

    @RequestMapping(value = "/persistence", method = RequestMethod.GET)
    @ResponseBody
    public String start() {
        return sessionFactory.openSession().getStatistics().toString();
    }
}
