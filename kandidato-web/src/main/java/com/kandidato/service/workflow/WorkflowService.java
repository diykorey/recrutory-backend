package com.kandidato.service.workflow;

import com.kandidato.persistence.entity.Flow;
import com.kandidato.persistence.entity.Person;
import com.kandidato.persistence.entity.Vacancy;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Interface for workflow services.
 */
public interface WorkflowService {

    /**
     * Returns the {@link Flow} with the specified id.
     *
     * @param flowId
     *
     * @return flow
     */
    Flow find(@PathVariable long flowId);

    /**
     * Creates a new {@link Flow}, which links the specified person to the vacancy.
     *
     * @param vacancyId
     * @param personId
     */
    Flow create(long vacancyId, long personId);

    /**
     * Creates a new {@link Flow}.
     *
     * @param flow
     */
    Flow create(Flow flow);

    /**
     * Removes the flow with the specified id.
     *
     * @param id
     */
    void remove(long id);

    /**
     * Retrieves a list of {@link Flow}s, for the specified vacancy.
     *
     * @param vacancyId
     *
     * @return list of flows or an empty list if no flows are present
     */
    List<Flow> findFlowsByVacancy(long vacancyId);

}
