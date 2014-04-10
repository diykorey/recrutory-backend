package com.kandidato.persistence.repository.flow;

import com.kandidato.persistence.entity.Flow;
import com.kandidato.persistence.repository.base.HibernateRepository;
import com.kandidato.persistence.repository.flow.query.FlowQuery;

import javax.persistence.EntityManager;

/**
 * Created by andriy on 4/8/14.
 */
public class FlowRepositoryImpl extends HibernateRepository<Flow, FlowQuery> implements FlowRepositoryCustom {

    public FlowRepositoryImpl(EntityManager entityManager, Class<Flow> entityClass) {
        super(entityManager, entityClass);
    }
}
