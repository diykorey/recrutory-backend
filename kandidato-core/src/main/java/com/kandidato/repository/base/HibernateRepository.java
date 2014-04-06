package com.kandidato.repository.base;

import com.kandidato.entity.Entity;
import com.kandidato.repository.query.HibernateQuery;
import org.hibernate.SessionFactory;

import java.util.Collection;

/**
 * Created by andriy on 4/6/14.
 */
public abstract class HibernateRepository<T extends Entity, Q extends HibernateQuery> implements Repository<T, Q> {

    private final SessionFactory sessionFactory;

    private final Class<T> entityClass;

    public HibernateRepository(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    @Override
    public Collection<T> query(Q query) {
        return this.sessionFactory.getCurrentSession().createCriteria(entityClass).add(query.toCriterion()).list();
    }
}
