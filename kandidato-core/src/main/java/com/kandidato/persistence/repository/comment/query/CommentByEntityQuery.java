package com.kandidato.persistence.repository.comment.query;

import com.kandidato.constants.CommentType;
import com.kandidato.persistence.repository.query.Query;

import javax.persistence.EntityManager;

/**
 * Query to retrieve comments by entity type.
 * <p/>
 * Created by andriy on 4/26/14.
 */
public class CommentByEntityQuery implements Query {

    private final long entityId;

    private final CommentType type;

    public CommentByEntityQuery(long entityId, CommentType type) {

        this.entityId = entityId;
        this.type = type;
    }

    @Override
    public <T> T accept(EntityManager entityManger) {

        return (T) entityManger.createQuery("from EntityComment c where c.class =:entityType and c.entity.id =:entityId order by c.creationDate desc").setParameter("entityType", this.type.name()).setParameter("entityId", entityId).getResultList();

    }
}
