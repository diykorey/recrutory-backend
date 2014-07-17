package com.kandidato.persistence.repository.flow.query;

import com.kandidato.persistence.entity.Flow;
import com.kandidato.persistence.repository.query.AbstractCriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Created by andriy on 7/17/14.
 */
public class FlowByVacancyQuery extends AbstractCriteriaQuery implements FlowQuery {

    private final long vacancyId;

    public FlowByVacancyQuery(long vacancyId) {
        super(Flow.class);
        this.vacancyId = vacancyId;
    }

    @Override
    public Criterion toCriterion() {
        return Restrictions.eq("vacancy.id", vacancyId);
    }
}
