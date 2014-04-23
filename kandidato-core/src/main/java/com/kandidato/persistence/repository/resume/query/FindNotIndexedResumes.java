package com.kandidato.persistence.repository.resume.query;

import com.kandidato.constants.ResumeState;
import com.kandidato.persistence.repository.query.HibernateQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class FindNotIndexedResumes implements ResumeQuery{
    @Override
    public Criterion toCriterion() {
        return Restrictions.or(//
                Restrictions.eq("state", ResumeState.NEW), Restrictions.eq("state", ResumeState.OLD));
    }
}
