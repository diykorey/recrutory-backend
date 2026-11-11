package com.kandidato.manager.vacancy;

import com.kandidato.constants.VacancyState;
import com.kandidato.model.dto.BaseUserDTO;
import com.kandidato.model.dto.VacancyDTO;
import com.kandidato.model.dto.input.TagsIn;
import com.kandidato.model.dto.input.VacancyIn;
import com.kandidato.persistence.entity.User;
import com.kandidato.security.model.CurrentProfile;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface VacancyManager {

    /**
     * Creates a new vacancy.
     *
     *
     * @param currentUser
     * @param vacancy
     * @return newly created vacancy
     */
    VacancyDTO create(CurrentProfile currentUser, VacancyIn vacancy);

    VacancyDTO update(Long vacancyId, VacancyIn vacancy);

    /**
     * Retrieves a {@link com.kandidato.persistence.entity.Vacancy} by its id.
     *
     * @param id
     * @return
     */
    VacancyDTO findById(long id);

    /**
     * Removes the {@link com.kandidato.model.dto.VacancyDTO}  with the specified id.
     *
     * @param id
     * @return removed vacancy
     */
    void remove(long id);

    /**
     * Retrieves a list of {@link com.kandidato.model.dto.VacancyDTO}ies, with the current state.
     * <p/>
     * Valid implementation should only return vacancies accessible by the current user.
     *
     * @param state
     * @param id
     * @return list of vacancies or an empty list
     */
    List<VacancyDTO> findByState(VacancyState state, Long id);

    /**
     * Retrieves a list of {@link com.kandidato.model.dto.VacancyDTO}ies, created by the specified user.
     * <p/>
     * Valid implementation should only return vacancies accessible by the current user.
     *
     * @param authorId
     * @return list of vacancies or an empty list
     */
    List<VacancyDTO> findByAuthor(long authorId);
    /**
     * Add a list of {@link com.kandidato.model.dto.input.TagsIn}s, to the specified vacancy.
     * <p/>
     *
     * @param vacancyId
     * @param tagsIn list of tags which should be added
     */
    VacancyDTO addTags(long vacancyId, TagsIn tagsIn);
    /**
     * Remove tag from  specified vacancy.
     * <p/>
     *
     * @param vacancyId
     * @param tagId
     */
    void removeTag(long vacancyId, long tagId);

    /**
     * Returns all vacancies that don't have any flow with given candidate.
     * @param candidateId Candidate ID.
     * @param page
     * @param size
     * @return List of vacancies.
     */
    List<VacancyDTO> findAvailable(long candidateId, long creatorId, int page, int size);

    void archiveVacancy(long id);

    List<VacancyDTO> findByState(VacancyState state, Long creatorId, String sortBy, String sortDirection,  int page, int size);

    List<VacancyDTO> filterByStateAndName(VacancyState state, String name, User creator, String sortBy, Sort.Direction sortDirection, int page, int size);
}
