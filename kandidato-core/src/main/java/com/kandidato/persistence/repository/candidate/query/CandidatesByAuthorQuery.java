package com.kandidato.persistence.repository.candidate.query;

import com.kandidato.persistence.entity.Candidate;
import com.kandidato.persistence.repository.query.AbstractCriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Created by andriy on 6/4/14.
 */
public class CandidatesByAuthorQuery extends AbstractCriteriaQuery implements CandidateQuery {

    private final long creatorId;

    public CandidatesByAuthorQuery(long creatorId) {
        super(Candidate.class);
        this.creatorId = creatorId;
    }


    @Override
    public Criterion toCriterion() {
        return Restrictions.eq("creator.id", creatorId);
    }

}
