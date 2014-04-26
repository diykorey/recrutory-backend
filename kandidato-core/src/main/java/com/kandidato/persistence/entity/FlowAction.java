package com.kandidato.persistence.entity;

import com.kandidato.constants.FlowState;
import com.kandidato.persistence.entity.comment.ActionComment;
import com.kandidato.persistence.entity.comment.CommentableEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@javax.persistence.Entity
@Table(name = "FLOW_ACTIONS")
public class FlowAction implements CommentableEntity<ActionComment> {

    @Id
    @GeneratedValue
    @Column(name = "FLOW_ACTION_ID")
    private Long id;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private FlowState state;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATION_TIME")
    private Date creationTime;

    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL)
    private List<ActionComment> comments = new ArrayList<>();

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

    public List<ActionComment> getComments() {
        return comments;
    }

    public void setComments(List<ActionComment> comments) {
        this.comments = comments;
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
