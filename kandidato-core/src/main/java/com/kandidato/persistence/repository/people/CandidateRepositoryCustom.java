package com.kandidato.persistence.repository.people;

import com.kandidato.persistence.entity.Candidate;
import com.kandidato.persistence.repository.base.Repository;
import com.kandidato.persistence.repository.people.query.CandidateQuery;

/**
 * Created by andriy on 4/10/14.
 */
public interface CandidateRepositoryCustom extends Repository<Candidate, CandidateQuery> {
}
