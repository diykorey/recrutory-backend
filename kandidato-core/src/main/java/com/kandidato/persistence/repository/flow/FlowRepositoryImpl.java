package com.kandidato.persistence.repository.flow;

import com.kandidato.persistence.entity.Flow;
import com.kandidato.persistence.repository.base.HibernateRepository;
import com.kandidato.persistence.repository.flow.query.FlowQuery;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * Created by andriy on 4/8/14.
 */
public class FlowRepositoryImpl extends HibernateRepository<Flow, FlowQuery> implements FlowRepositoryCustom {

    @Autowired
    public FlowRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Flow.class);
    }
}
