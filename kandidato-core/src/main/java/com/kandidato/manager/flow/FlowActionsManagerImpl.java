package com.kandidato.manager.flow;

import com.kandidato.model.dto.FlowActionDTO;
import com.kandidato.model.dto.input.DescriptionIn;
import com.kandidato.model.dto.input.FlowActionIn;
import com.kandidato.persistence.entity.Flow;
import com.kandidato.persistence.entity.FlowAction;
import com.kandidato.persistence.repository.flow.ActionRepository;
import com.kandidato.persistence.repository.flow.FlowRepository;
import com.kandidato.util.DozerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class FlowActionsManagerImpl implements FlowActionsManager{

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private FlowRepository flowRepository;

    @Autowired
    private DozerUtil dozerUtil;

    @Override
    public FlowActionDTO create(FlowActionIn action) {

        Flow flow = flowRepository.findOne(action.getFlowId());

        FlowAction flowAction = new FlowAction();
        flowAction.setState(action.getState());
        flowAction.setDescription(action.getDescription());
        flowAction.setCreationTime(new Date());

        flow.getActions().add(flowAction);
        flowAction.setFlow(flow);

        return dozerUtil.map(actionRepository.save(flowAction), FlowActionDTO.class);
    }

    @Override
    public List<FlowActionDTO> actions(Long flowId) {
        List<FlowAction> actions = actionRepository.findByFlowId(flowId);
        return dozerUtil.map(actions, FlowActionDTO.class);
    }

    @Override
    public FlowActionDTO updateDescription(Long actionId, DescriptionIn description) {
        FlowAction action = actionRepository.findOne(actionId);
        action.setDescription(description.getDescription());
        actionRepository.save(action);
        return dozerUtil.map(action, FlowActionDTO.class);
    }
}
