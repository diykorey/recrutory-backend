package com.kandidato.persistence.repository.query;

import javax.persistence.EntityManager;

/**
 * Interface to encapsulate queries to {@code com.kandidato.persistence.repository.base.Repository}.
 * <p/>
 * Created by andriy on 4/3/14.
 */
public interface Query {

    /**
     * Provides the query with an instance of {@link javax.persistence.EntityManager}.
     * <p/>
     * Most of the queries will need {@link javax.persistence.EntityManager} instance, to be able to create criteria or
     * hql queries and pass parameters to them. At this point, a query is already aware of the entityManager -
     * it also makes sense to let the query execute itself, and return the result.
     *
     * @param entityManger to execute the query against
     * @param <T>          type of the result
     * @return result of the execution
     */
    <T> T accept(EntityManager entityManger);

}
