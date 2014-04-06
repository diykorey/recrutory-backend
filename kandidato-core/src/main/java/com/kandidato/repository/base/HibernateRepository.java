package com.kandidato.repository.base;

import com.kandidato.entity.Entity;
import com.kandidato.repository.query.HibernateQuery;
import org.hibernate.SessionFactory;

import java.util.Collection;

/**
 * Created by andriy on 4/6/14.
 */
public abstract class HibernateRepository<T extends Entity, Q extends HibernateQuery> extends AbstractRepository<T, Q> {

    protected HibernateRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    protected Collection<T> query(Q query, Class<T> clazz) {
        return this.sessionFactory.getCurrentSession().createCriteria(clazz).add(query.toCriterion()).list();
    }
}
