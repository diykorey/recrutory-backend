package com.kandidato.manager.search;

import com.kandidato.model.dto.BaseCandidateDTO;

import java.util.List;

public interface CandidateSearchManager {
    List<BaseCandidateDTO> findCandidateByVacancyId(String query, long vacancyId, long creatorId, int page, int size);

    List<BaseCandidateDTO> findCandidates(String requirements, long creatorId, int page, int size);

    List<Long> findCandidateIdsByVacancyId(long vacancyId, long creatorId);
}
