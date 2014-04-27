package com.kandidato.persistence.repository.comment.query;

import com.kandidato.constants.CommentType;
import com.kandidato.persistence.repository.query.HibernateQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Query to retrieve comments by entity type.
 *
 * Created by andriy on 4/26/14.
 */
public class CommentByEntityQuery implements HibernateQuery {

    private final long entityId;

    private final CommentType type;

    public CommentByEntityQuery(long entityId, CommentType type) {
        this.entityId = entityId;
        this.type = type;
    }

    @Override
    public Criterion toCriterion() {
        return Restrictions.and(Restrictions.eq("entity.id", entityId), Restrictions.eq("class", type.getDiscriminator()));
    }
}
