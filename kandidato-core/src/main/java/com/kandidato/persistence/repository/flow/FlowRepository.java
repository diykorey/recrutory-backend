package com.kandidato.persistence.repository.flow;

import com.kandidato.persistence.entity.Flow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andriy on 4/8/14.
 */
@Repository
public interface FlowRepository extends FlowRepositoryCustom, JpaRepository<Flow, Long> {
}
