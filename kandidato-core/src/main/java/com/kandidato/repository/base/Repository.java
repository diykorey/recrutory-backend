package com.kandidato.repository.base;

import com.kandidato.entity.Entity;
import com.kandidato.repository.query.Query;

import java.util.Collection;

/**
 * Interface for data repositories.
 * <p/>
 * Repositories are not meant to provide specific data access methods.
 * <p/>
 * To query the database, consider creating {@link com.kandidato.repository.query.Query} classes and using
 * {@link #query(com.kandidato.repository.query.Query)} method,
 * instead of adding methods to the repository itself.
 * <p/>
 * Created by andriy on 4/3/14.
 */
public interface Repository<T extends Entity, Q extends Query> {

    void add(T entity);

    void remove(T entity);

    void update(T entity);

    Collection<T> query(Q q);


}
