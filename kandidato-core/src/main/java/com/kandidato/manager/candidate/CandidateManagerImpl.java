package com.kandidato.manager.candidate;

import com.kandidato.persistence.entity.Candidate;
import com.kandidato.persistence.repository.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andriy on 5/15/14.
 */
@Component
public class CandidateManagerImpl implements CandidateManager {

    @Autowired
    private CandidateRepository repository;

    @Override
    public Candidate create(Candidate candidate) {
        return this.repository.save(candidate);
    }

    @Override
    public Candidate findById(long id) {
        return this.repository.findOne(id);
    }

    @Override
    public void remove(long id) {
        this.repository.delete(id);
    }
}
