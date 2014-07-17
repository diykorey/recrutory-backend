package com.kandidato.persistence.repository.flow;

import com.kandidato.persistence.entity.FlowAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<FlowAction, Long> {
}
