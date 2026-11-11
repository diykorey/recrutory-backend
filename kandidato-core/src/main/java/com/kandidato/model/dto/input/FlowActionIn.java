package com.kandidato.model.dto.input;

import com.kandidato.constants.FlowState;

import javax.validation.constraints.NotNull;

public class FlowActionIn {

    @NotNull
    private FlowState state;

    private String description;

    @NotNull
    private Long flowId;


    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FlowState getState() {
        return state;
    }

    public void setState(FlowState state) {
        this.state = state;
    }
}
