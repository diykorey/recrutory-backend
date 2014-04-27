package com.kandidato.persistence.entity.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kandidato.persistence.entity.User;
import com.kandidato.persistence.entity.Vacancy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by andriy on 4/21/14.
 */
@Entity
@DiscriminatorValue(VacancyComment.DISCRIMINATOR)
public class VacancyComment extends EntityComment<Vacancy> {

    public static final String DISCRIMINATOR = "VACANCY";

    public VacancyComment() {
        //JPA Compatibility
    }

    public VacancyComment(String comment, Date creationDate, User author, Vacancy entity) {
        super(comment, creationDate, author);
        this.setEntity(entity);
    }

    @ManyToOne(targetEntity = Vacancy.class)
    @JoinColumn(name = "ENTITY_ID")
    @JsonIgnore
    private Vacancy entity;

    @Override
    public Vacancy getEntity() {
        return entity;
    }

    @Override
    public void setEntity(Vacancy entity) {
        this.entity = entity;
        this.entity.getComments().add(this);
    }
}
