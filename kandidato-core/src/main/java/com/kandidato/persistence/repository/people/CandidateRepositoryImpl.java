package com.kandidato.persistence.repository.people;

import com.kandidato.persistence.entity.Candidate;
import com.kandidato.persistence.repository.base.HibernateRepository;
import com.kandidato.persistence.repository.people.query.CandidateQuery;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * Created by andriy on 4/10/14.
 */
public class CandidateRepositoryImpl extends HibernateRepository<Candidate, CandidateQuery> implements CandidateRepositoryCustom {

    @Autowired
    public CandidateRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Candidate.class);
    }
}