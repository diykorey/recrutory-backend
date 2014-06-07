package com.kandidato.persistence.entity.comment;

import com.kandidato.persistence.entity.CreatorAware;
import com.kandidato.persistence.entity.Entity;
import com.kandidato.persistence.entity.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by andriy on 4/21/14.
 */
@javax.persistence.Entity
@Table(name = "COMMENTS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ENTITY_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class EntityComment<T extends Entity> implements Entity, CreatorAware {

    @Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue
    protected Long id;

    @Column(name = "STATEMENT", nullable = false)
    protected String comment;

    @Column(name = "CREATION_DATE", nullable = false)
    protected Date creationDate = new Date();

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    protected User creator;

    protected EntityComment() {
        //JPA compatibility
    }

    public EntityComment(String comment, Date creationDate, User creator) {
        this.comment = comment;
        this.creationDate = creationDate;
        this.creator = creator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract T getEntity();

    public abstract void setEntity(T entity);

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityComment)) return false;

        EntityComment that = (EntityComment) o;

        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = 31 + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        return result;
    }
}
