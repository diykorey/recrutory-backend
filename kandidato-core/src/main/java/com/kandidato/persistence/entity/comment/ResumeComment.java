package com.kandidato.persistence.entity.comment;

import com.kandidato.persistence.entity.Resume;
import com.kandidato.persistence.entity.User;
import com.kandidato.persistence.entity.Vacancy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by andriy on 4/26/14.
 */
@Entity
@DiscriminatorValue("RESUME")
public class ResumeComment extends EntityComment<Resume> {

    public ResumeComment() {
        //JPA Compatibility
    }

    public ResumeComment(String comment, Date creationDate, User author, Resume entity) {
        super(comment, creationDate, author);
        this.setEntity(entity);
    }

    @ManyToOne(targetEntity = Vacancy.class)
    @JoinColumn(name = "ENTITY_ID")
    private Resume entity;


    @Override
    public Resume getEntity() {
        return entity;
    }

    @Override
    public void setEntity(Resume entity) {
        this.entity = entity;
        this.entity.getComments().add(this);
    }
}
