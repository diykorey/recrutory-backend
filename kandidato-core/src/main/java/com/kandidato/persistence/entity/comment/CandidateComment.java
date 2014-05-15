package com.kandidato.persistence.entity.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kandidato.persistence.entity.Candidate;
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
@DiscriminatorValue(CandidateComment.DISCRIMINATOR)
public class CandidateComment extends EntityComment<Candidate> {

    public static final String DISCRIMINATOR = "CANDIDATE";

    public CandidateComment() {
        //JPA Compatibility
    }

    public CandidateComment(String comment, Date creationDate, User author, Candidate entity) {
        super(comment, creationDate, author);
        this.setEntity(entity);
    }

    @ManyToOne(targetEntity = FlowAction.class)
    @JoinColumn(name = "ENTITY_ID")
    @JsonIgnore
    private Candidate entity;

    @Override
    public Candidate getEntity() {
        return entity;
    }

    @Override
    public void setEntity(Candidate entity) {
        this.entity = entity;
        this.entity.getComments().add(this);
    }
}
