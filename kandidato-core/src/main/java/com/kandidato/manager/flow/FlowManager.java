package com.kandidato.manager.flow;


import com.kandidato.model.dto.FlowDTO;
import com.kandidato.model.dto.FlowActionDTO;
import com.kandidato.model.dto.input.FlowIn;

import java.util.List;

/**
 * Created by andriy on 4/8/14.
 */
public interface FlowManager {

    /**
     * Creates a new {@link com.kandidato.persistence.entity.Flow}, which links the specified canidate to the vacancy.
     *
     */
    List<FlowDTO> create(List<FlowIn> flows);

    /**
     * Removes the flow with the specified id.
     *
     * @param id
     */
    void remove(long id);

    /**
     * Retrieves a list of {@link com.kandidato.model.dto.FlowDTO}s, for the specified vacancy.
     *
     * @param vacancyId
     * @return list of flows or an empty list if no flows are present
     */
    List<FlowDTO> findByVacancy(long vacancyId);

    /**
     * Returns the {@link com.kandidato.model.dto.FlowDTO} with the matching id.
     *
     * @param flowId
     * @return
     */
    FlowDTO find(long flowId);

    /**
     * Retrieves a list of {@link com.kandidato.model.dto.FlowDTO}s, for the specified candidate.
     *
     * @param candidateId
     * @return list of flows or an empty list if no flows are present
     */
    List<FlowDTO> findByCandidate(long candidateId);
    /**
     * Returns the all {@link com.kandidato.model.dto.FlowDTO} in the system.
     *
     * @return
     */
    List<FlowDTO> findFlowsByUser();

    
}
