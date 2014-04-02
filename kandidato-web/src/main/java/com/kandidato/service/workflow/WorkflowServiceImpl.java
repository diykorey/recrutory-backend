package com.kandidato.service.workflow;

import com.kandidato.entity.Flow;
import com.kandidato.entity.Person;
import com.kandidato.entity.Vacancy;

/**
 RESTful implementation of {@link com.kandidato.service.workflow.WorkflowService}.
 */
public class WorkflowServiceImpl implements WorkflowService {

    @Override
    public Flow create(Vacancy vacancy, Person person) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public Flow delete(long id) {
        throw new RuntimeException("Not yet implemented");
    }
}
