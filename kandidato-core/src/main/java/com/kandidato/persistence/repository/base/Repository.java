package com.kandidato.persistence.repository.base;

import com.kandidato.persistence.entity.Entity;
import com.kandidato.persistence.repository.query.Query;

import java.util.Collection;

/**
 * Interface for data repositories.
 * <p/>
 * Repositories are not meant to provide specific data access methods.
 * <p/>
 * To query the database, consider creating {@link com.kandidato.persistence.repository.query.Query} classes and using
 * {@link #query(com.kandidato.persistence.repository.query.Query)} method,
 * instead of adding methods to the repository itself.
 * <p/>
 * Created by andriy on 4/3/14.
 */
public interface Repository<T extends Entity, Q extends Query> {

    Collection<T> query(Q query);

}
