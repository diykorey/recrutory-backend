package com.kandidato.persistence.repository.vacancy;

import com.kandidato.constants.SearchIndexState;
import com.kandidato.constants.VacancyState;
import com.kandidato.persistence.entity.User;
import com.kandidato.persistence.entity.Vacancy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query("select v from Vacancy v where v.state= :state  and v.creator.id= :creatorId")
    List<Vacancy> find(@Param("state")VacancyState state, @Param("creatorId") Long creatorId);

    //@Query("select v from Vacancy v where v.creator.id = ?1")
    List<Vacancy> findByCreatorId(Long creatorId);

    @Query("select v from Vacancy v where v not in (select f.vacancy from Flow f where f.candidate.id = :candidateId) and v.creator.id= :creatorId")
    List<Vacancy> findNotConnectedToCandidate(@Param("candidateId") Long candidateId, @Param("creatorId") Long creatorId, Pageable pageable);

    @Query("select v from Vacancy v where v.state= :state  and v.creator.id= :creatorId")
    List<Vacancy> find(@Param("state") VacancyState state, @Param("creatorId") Long creatorId, Pageable pageRequest);

    @Query("select v from Vacancy v where v.creator.id= :creatorId")
    List<Vacancy> find(@Param("creatorId") Long creatorId, Pageable pageRequest);

    List<Vacancy> findByCreator_IdAndNumberIn(Long id, Collection<String> vacancyNumbers, Pageable pageRequest);

    List<Vacancy> findBySearchStateIn(Collection<SearchIndexState> indexStates, Pageable pageRequest);

    List<Vacancy> findByNameContainingAndCreator(String name, User creator, Pageable pageRequest);

    List<Vacancy> findByNameContainingAndStateAndCreator(String name, VacancyState state, User creator, Pageable pageRequest);
}
