package com.kandidato.persistence.repository.vacancy.query;

import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.persistence.repository.query.AbstractCriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Created by andriy on 4/3/14.
 */
public class VacancyByAuthorQuery extends AbstractCriteriaQuery implements VacancyQuery {

    private final long creatorId;

    public VacancyByAuthorQuery(long creatorId) {
        super(Vacancy.class);
        this.creatorId = creatorId;
    }

    @Override
    public Criterion toCriterion() {
        return Restrictions.eq("creator.id", creatorId);
    }
}
