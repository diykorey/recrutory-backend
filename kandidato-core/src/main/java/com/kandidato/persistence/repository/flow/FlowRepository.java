package com.kandidato.persistence.repository.flow;

import com.kandidato.persistence.entity.Flow;
import com.kandidato.persistence.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowRepository extends JpaRepository<Flow, Long> {

    //@Query("select f from Flow f where f.vacancy.id = ?1")
    List<Flow> findByVacancyId(Long vacancyId);

    List<Flow> findByCandidateId(Long candidateId);

    List<Flow> findByCreatorId(Long creatorId);
}
