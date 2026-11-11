package com.kandidato.persistence.repository.flow.query;

import com.kandidato.persistence.entity.Flow;
import com.kandidato.persistence.repository.query.AbstractCriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Created by andriy on 7/17/14.
 */
public class FlowByCandidateQuery extends AbstractCriteriaQuery implements FlowQuery {

    private final long candidateId;

    public FlowByCandidateQuery(long candidateId) {
        super(Flow.class);
        this.candidateId = candidateId;
    }

    @Override
    public Criterion toCriterion() {
        return Restrictions.eq("candidate.id", candidateId);
    }
}
