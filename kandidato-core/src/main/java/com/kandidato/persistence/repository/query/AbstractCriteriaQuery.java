package com.kandidato.persistence.repository.query;

import org.hibernate.Session;

import javax.persistence.EntityManager;

/**
 * Abstract class, implementing the {@link #accept(javax.persistence.EntityManager)} method from {@link com.kandidato.persistence.repository.query.CriteriaQuery}.
 * <p/>
 * Created by andriy on 7/17/14.
 */
public abstract class AbstractCriteriaQuery implements CriteriaQuery {


    private Class<?> entityClass;

    protected AbstractCriteriaQuery(Class<?> entityClass) {
        this.entityClass = entityClass;
    }


    @Override
    public <T> T accept(EntityManager entityManager) {
        return (T) entityManager.unwrap(Session.class).createCriteria(entityClass).add(toCriterion()).list();
    }

}
