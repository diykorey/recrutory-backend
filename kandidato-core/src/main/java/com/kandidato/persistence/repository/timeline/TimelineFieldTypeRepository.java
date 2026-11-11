package com.kandidato.persistence.repository.timeline;

import com.kandidato.persistence.entity.TimelineFieldType;
import com.kandidato.persistence.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimelineFieldTypeRepository extends JpaRepository<TimelineFieldType, Long> {
    List<TimelineFieldType> findByCreatorId(Long creatorId);
}
