package com.kandidato.persistence.repository.vacancy.query;

import org.hibernate.criterion.Criterion;

/**
 * Created by andriy on 4/3/14.
 */
public class RemoveVacancyQuery implements VacancyQuery {

    private final long vacancyId;

    public RemoveVacancyQuery(long vacancyId) {
        this.vacancyId = vacancyId;
    }

    @Override
    public Criterion toCriterion() {
        return null;
    }
}
