package com.kandidato.persistence.repository.resume.query;

import com.kandidato.constants.ResumeState;
import com.kandidato.persistence.entity.Resume;
import com.kandidato.persistence.repository.query.AbstractCriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class FindNotIndexedResumes extends AbstractCriteriaQuery implements ResumeQuery {

    public FindNotIndexedResumes() {
        super(Resume.class);
    }

    @Override
    public Criterion toCriterion() {
        return Restrictions.or(//
                Restrictions.eq("state", ResumeState.NEW), Restrictions.eq("state", ResumeState.OLD));
    }
}
