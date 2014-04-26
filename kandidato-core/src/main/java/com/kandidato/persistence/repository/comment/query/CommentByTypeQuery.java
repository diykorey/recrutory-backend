package com.kandidato.persistence.repository.comment.query;

import com.kandidato.persistence.repository.query.HibernateQuery;
import org.hibernate.criterion.Criterion;

/**
 * Query to retrieve comments by entity type.
 *
 * Created by andriy on 4/26/14.
 */
public class CommentByTypeQuery implements HibernateQuery {

    @Override
    public Criterion toCriterion() {
        return null;
    }
}
