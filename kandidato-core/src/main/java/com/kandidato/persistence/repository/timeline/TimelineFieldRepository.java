package com.kandidato.persistence.repository.timeline;

import com.kandidato.persistence.entity.TimelineField;
import com.kandidato.persistence.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimelineFieldRepository extends JpaRepository<TimelineField, Long> {
}
