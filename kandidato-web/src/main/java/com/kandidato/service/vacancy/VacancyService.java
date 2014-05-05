package com.kandidato.service.vacancy;

import com.kandidato.constants.VacancyState;
import com.kandidato.persistence.entity.Vacancy;

import java.util.List;

/**
 * Interface for vacancy services.
 */
public interface VacancyService {

    /**
     * Creates the vacancy.
     *
     * @param vacancy
     */
    Vacancy create(Vacancy vacancy);

    /**
     * Retrieves a {@link Vacancy} by its id.
     *
     * @param id
     * @return
     */
    Vacancy findById(long id);

    /**
     * Removes the {@link Vacancy}  with the specified id.
     *
     * @param id
     */
    void remove(long id);

    /**
     * Retrieves a list of {@link Vacancy}ies, with the current state.
     * <p/>
     * Valid implementation should only return vacancies accessible by the current user.
     *
     * @param state
     * @return list of vacancies or an empty list
     */
    List<Vacancy> findByState(VacancyState state);

    /**
     * Retrieves a list of {@link Vacancy}ies, created by the specified user.
     * <p/>
     * Valid implementation should only return vacancies accessible by the current user.
     *
     * @param authorId
     * @return list of vacancies or an empty list
     */
    List<Vacancy> findByAuthor(long authorId);

}
