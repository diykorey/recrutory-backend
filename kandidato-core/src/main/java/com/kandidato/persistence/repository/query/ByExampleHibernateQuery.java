package com.kandidato.persistence.repository.query;

import com.kandidato.persistence.entity.Entity;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

/**
 * Transforms an instance of an {@link Entity} into a hibernate {@link org.hibernate.criterion.Criterion}.
 *
 * Created by andriy on 4/9/14.
 */
public abstract class ByExampleHibernateQuery<T extends Entity> extends AbstractCriteriaQuery {

    private final T example;

    protected ByExampleHibernateQuery(T example) {
        super(example.getClass());
        this.example = example;
    }

    @Override
    public Criterion toCriterion() {
        return Example.create(example);
    }
}
