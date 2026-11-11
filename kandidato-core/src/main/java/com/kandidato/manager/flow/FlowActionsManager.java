package com.kandidato.manager.flow;

import com.kandidato.model.dto.FlowActionDTO;
import com.kandidato.model.dto.input.DescriptionIn;
import com.kandidato.model.dto.input.FlowActionIn;

import java.util.List;

public interface FlowActionsManager {
    FlowActionDTO create(FlowActionIn action);

    List<FlowActionDTO> actions(Long flowId);

    FlowActionDTO updateDescription(Long actionId, DescriptionIn description);
}
