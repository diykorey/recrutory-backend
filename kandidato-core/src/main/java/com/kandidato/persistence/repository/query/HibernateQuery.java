package com.kandidato.persistence.repository.query;


import org.hibernate.criterion.Criterion;

/**
 * Query, to be used with Hibernate persistence.
 */
public interface HibernateQuery extends Query {

    /**
     * Transforms ths query into hibernate criterion.
     *
     * @return hibernate criterion
     */
    Criterion toCriterion();

}
