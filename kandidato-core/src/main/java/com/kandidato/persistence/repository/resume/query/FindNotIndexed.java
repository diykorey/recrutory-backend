package com.kandidato.persistence.repository.resume.query;

import com.kandidato.persistence.repository.query.HibernateQuery;
import org.hibernate.criterion.Criterion;

public class FindNotIndexed implements HibernateQuery{
    @Override
    public Criterion toCriterion() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
