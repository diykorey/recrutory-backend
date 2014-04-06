package com.kandidato.persistence.repository.vacancy.query;

import org.hibernate.criterion.Criterion;

/**
 * Created by andriy on 4/3/14.
 */
public class VacancyByAuthorQuery implements VacancyQuery {

    private final long creatorId;

    public VacancyByAuthorQuery(long creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public Criterion toCriterion() {
        return null;
    }
}
