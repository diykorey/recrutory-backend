package com.kandidato.service.workflow;

import com.kandidato.entity.Flow;
import com.kandidato.entity.Person;
import com.kandidato.entity.Vacancy;

/**
 * Interface for workflow services.
 */
public interface WorkflowService {

    /**
     * Creates a new {@link Flow}, which links the specified person to the vacancy.
     *
     * @param vacancy
     * @param person
     */
    Flow create(Vacancy vacancy, Person person);

    /**
     * Removes the flow with the specified id.
     *
     * @param id
     * @return removed flow
     */
    Flow delete(long id);

}
