package com.kandidato.persistence.repository.query;

import com.kandidato.persistence.entity.Entity;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

/**
 * Created by andriy on 4/9/14.
 */
public abstract class ByExmpleHibernateQuery<T extends Entity> implements HibernateQuery {

    private final T example;

    protected ByExmpleHibernateQuery(T example) {
        this.example = example;
    }

    @Override
    public Criterion toCriterion() {
        return Example.create(example);
    }
}
