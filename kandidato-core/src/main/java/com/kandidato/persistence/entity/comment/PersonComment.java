package com.kandidato.persistence.entity.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kandidato.persistence.entity.FlowAction;
import com.kandidato.persistence.entity.Person;
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
@DiscriminatorValue(PersonComment.DISCRIMINATOR)
public class PersonComment extends EntityComment<Person> {

    public static final String DISCRIMINATOR = "PERSON";

    public PersonComment() {
        //JPA Compatibility
    }

    public PersonComment(String comment, Date creationDate, User author, Person entity) {
        super(comment, creationDate, author);
        this.setEntity(entity);
    }

    @ManyToOne(targetEntity = FlowAction.class)
    @JoinColumn(name = "ENTITY_ID")
    @JsonIgnore
    private Person entity;

    @Override
    public Person getEntity() {
        return entity;
    }

    @Override
    public void setEntity(Person entity) {
        this.entity = entity;
        this.entity.getComments().add(this);
    }
}
