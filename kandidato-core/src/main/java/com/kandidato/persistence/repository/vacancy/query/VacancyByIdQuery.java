package com.kandidato.persistence.repository.vacancy.query;

import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.persistence.repository.query.AbstractCriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Created by andriy on 4/3/14.
 */
public class VacancyByIdQuery extends AbstractCriteriaQuery implements VacancyQuery {

    private final long vacancyId;

    public VacancyByIdQuery(long vacancyId) {
        super(Vacancy.class);
        this.vacancyId = vacancyId;
    }

    @Override
    public Criterion toCriterion() {
        return Restrictions.idEq(vacancyId);
    }
}
