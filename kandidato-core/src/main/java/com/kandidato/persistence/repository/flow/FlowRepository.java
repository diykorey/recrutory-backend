package com.kandidato.persistence.repository.flow;

import com.kandidato.persistence.entity.Flow;
import com.kandidato.persistence.repository.base.Repository;
import com.kandidato.persistence.repository.flow.query.FlowQuery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by andriy on 4/8/14.
 */
@org.springframework.stereotype.Repository
public interface FlowRepository extends FlowRepositoryCustom, JpaRepository<Flow, Long>, Repository<Flow, FlowQuery> {
}
