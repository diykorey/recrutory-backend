package com.kandidato.persistence.repository.base;

import com.kandidato.persistence.entity.Entity;
import com.kandidato.persistence.repository.query.HibernateQuery;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * Created by andriy on 4/6/14.
 */
public abstract class HibernateRepository<T extends Entity, Q extends HibernateQuery> implements Repository<T, Q> {

    protected final EntityManager entityManager;

    private final Class<T> entityClass;

    public HibernateRepository(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    @Override
    public Collection<T> query(Q query) {
        return this.entityManager.unwrap(Session.class).createCriteria(entityClass).add(query.toCriterion()).list();
    }
}
