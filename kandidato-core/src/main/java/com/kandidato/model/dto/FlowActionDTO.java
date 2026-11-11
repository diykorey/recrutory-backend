package com.kandidato.model.dto;

import com.kandidato.constants.FlowState;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class FlowActionDTO implements Dto {

    private Long id;

    @NotNull
    private FlowState state;
    private String description;
    private Date creationTime;

    @NotNull
    private Long flowId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FlowState getState() {
        return state;
    }

    public void setState(FlowState state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }
}
