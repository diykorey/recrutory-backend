package com.kandidato.persistence.repository.candidate.query;


import com.kandidato.persistence.entity.Candidate;
import com.kandidato.persistence.repository.query.AbstractCriteriaQuery;
import org.hibernate.criterion.Criterion;

public class FindCandidatesByResumes extends AbstractCriteriaQuery implements CandidateQuery {

    public FindCandidatesByResumes() {
        super(Candidate.class);
    }

    @Override
    public Criterion toCriterion() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
