package com.kandidato.repository.base;

import com.kandidato.entity.Entity;
import com.kandidato.entity.Vacancy;
import com.kandidato.repository.query.Query;
import com.kandidato.repository.vacancy.query.VacancyQuery;
import org.hibernate.SessionFactory;

import java.util.Collection;

/**
 * Abstract repository, with basic CRUD operations.
 *
 * Created by andriy on 4/4/14.
 */
public abstract class AbstractRepository<T extends Entity, Q extends Query> implements Repository<T, Q> {


    protected final SessionFactory sessionFactory;

    protected AbstractRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(T entity) {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void remove(T entity) {
        this.sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public void update(T entity) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }
}
