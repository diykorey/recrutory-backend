package com.kandidato.manager.search.vacancy;

import com.kandidato.model.dto.VacancyDTO;

import java.util.Optional;
import java.util.Set;

public interface VacancySearchManager {
    Optional<Set<VacancyDTO>> findVacancies(String query, long creatorId, int page, int size);
}
