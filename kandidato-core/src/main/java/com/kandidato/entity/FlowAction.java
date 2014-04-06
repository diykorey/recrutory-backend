package com.kandidato.entity;

import com.kandidato.constants.FlowState;

import javax.persistence.*;
import java.util.Date;

@javax.persistence.Entity
@Table(name = "FLOW_ACTIONS")
public class FlowAction implements Entity {

    @Id
    @GeneratedValue
    @Column(name = "FLOW_ACTION_ID")
    private long id;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private FlowState state;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATION_TIME")
    private Date creationTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
