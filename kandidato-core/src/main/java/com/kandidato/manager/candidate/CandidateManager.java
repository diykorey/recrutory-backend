package com.kandidato.manager.candidate;

import com.kandidato.persistence.entity.Candidate;

import java.util.List;

/**
 * Created by andriy on 5/15/14.
 */
public interface CandidateManager {


    /**
     * Creates a new candidate.
     *
     * @param vacancy
     * @return newly created candidate
     */
    Candidate create(Candidate vacancy);

    /**
     * Retrieves a {@link com.kandidato.persistence.entity.Candidate} by its id.
     *
     * @param id
     * @return
     */
    Candidate findById(long id);

    /**
     * Removes the {@link Candidate} with the specified id.
     *
     * @param id
     */
    void remove(long id);

    /**
     * Updates the existing candidate.
     *
     * @param candidate
     */
    void update(Candidate candidate);

    /**
     * Retrieves the list of {@link com.kandidato.persistence.entity.Candidate}s, posted by the specified user.
     *
     * @param ownerId
     * @return list of {@link com.kandidato.persistence.entity.Candidate}s
     */
    List<Candidate> findByOwner(long ownerId);
}
