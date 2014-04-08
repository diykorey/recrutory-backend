package com.kandidato.dto;

import com.kandidato.constants.FlowState;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

public class FlowActionDto implements Dto {

    private Long id;
    private FlowState state;

    private String description;
    private Date creationTime;

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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
        builder.append(": {");
        builder.append("id: ");
        builder.append(id);
        builder.append(", creationTime: ");
        builder.append(creationTime);
        builder.append(", state: ");
        builder.append(state);
        builder.append(", description: ");
        builder.append(description);
        builder.append("}");
        return builder.toString();
    }
}
