package com.kandidato.persistence.repository.candidate.query;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Created by andriy on 6/4/14.
 */
public class CandidatesByAuthorQuery implements CandidateQuery {

    private final long creatorId;

    public CandidatesByAuthorQuery(long creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public Criterion toCriterion() {
        return Restrictions.eq("creator.id", creatorId);
    }

}
