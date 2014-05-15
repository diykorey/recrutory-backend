package com.kandidato.persistence.repository.candidate;

import com.kandidato.persistence.entity.Candidate;
import com.kandidato.persistence.repository.candidate.query.CandidateQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andriy on 4/10/14.
 */
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>, com.kandidato.persistence.repository.base.Repository<Candidate, CandidateQuery>{
}
