package com.kandidato.persistence.repository.query;

import javax.persistence.EntityManager;

/**
 * Wrapper around HQL string.
 * <p/>
 * Created by andriy on 6/30/14.
 */
public class HqlQuery implements Query {

    private final String query;

    public HqlQuery(String query) {
        this.query = query;
    }

    @Override
    public <T> T accept(EntityManager entityManger) {
        return (T) entityManger.createQuery(query).getResultList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HqlQuery)) return false;

        HqlQuery hqlQuery = (HqlQuery) o;

        if (query != null ? !query.equals(hqlQuery.query) : hqlQuery.query != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return query != null ? query.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "HqlQuery{" +
                "query='" + query + '\'' +
                '}';
    }
}
