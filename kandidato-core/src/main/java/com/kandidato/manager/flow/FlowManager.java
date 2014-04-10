package com.kandidato.manager.flow;

import com.kandidato.persistence.entity.Flow;

import java.util.List;

/**
 * Created by andriy on 4/8/14.
 */
public interface FlowManager {

    /**
     * Creates a new {@link com.kandidato.persistence.entity.Flow}, which links the specified person to the vacancy.
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
    List<Flow> findByVacancy(long vacancyId);

    /**
     * Returns the {@link Flow} with the matching id.
     *
     * @param flowId
     * @return
     */
    Flow find(long flowId);
}
