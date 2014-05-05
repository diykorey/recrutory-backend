package com.kandidato.persistence.entity.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kandidato.constants.CommentType;
import com.kandidato.persistence.entity.FlowAction;
import com.kandidato.persistence.entity.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by andriy on 4/21/14.
 */
@Entity
@DiscriminatorValue(ActionComment.DISCRIMINATOR)
public class ActionComment extends EntityComment<FlowAction> {

    public static final String DISCRIMINATOR = "ACTION";

    public ActionComment() {
        //JPA Compatibility
    }

    public ActionComment(String comment, Date creationDate, User author, FlowAction entity) {
        super(comment, creationDate, author);
        this.setEntity(entity);
    }

    @ManyToOne(targetEntity = FlowAction.class)
    @JoinColumn(name = "ENTITY_ID")
    @JsonIgnore
    private FlowAction entity;

    @Override
    public FlowAction getEntity() {
        return entity;
    }

    @Override
    public void setEntity(FlowAction entity) {
        this.entity = entity;
        this.entity.getComments().add(this);
    }

}
