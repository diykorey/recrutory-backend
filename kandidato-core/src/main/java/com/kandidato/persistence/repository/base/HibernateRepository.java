package com.kandidato.persistence.repository.base;

import com.kandidato.persistence.entity.Entity;
import com.kandidato.persistence.repository.query.CriteriaQuery;
import com.kandidato.persistence.repository.query.HqlQuery;
import com.kandidato.persistence.repository.query.Query;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * Created by andriy on 4/6/14.
 */
public abstract class HibernateRepository<T extends Entity, Q extends Query> implements Repository<T, Q> {

    protected final EntityManager entityManager;

    private final Class<T> entityClass;

    public HibernateRepository(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    @Override
    public Collection<T> query(Q query) {
        return query.accept(this.entityManager);
//        if (query instanceof CriteriaQuery) {
//            CriteriaQuery criteriaQuery = (CriteriaQuery) query;
//            return this.entityManager.unwrap(Session.class).createCriteria(entityClass).add(criteriaQuery.toCriterion()).list();
//        } else if (query instanceof HqlQuery) {
//            HqlQuery hqlQuery = (HqlQuery) query;
//            return this.entityManager.unwrap(Session.class).createQuery(hqlQuery.getHql()).list();
//        } else {
//            throw new IllegalArgumentException("Unsupported query type :" + query);
//        }
    }
}
