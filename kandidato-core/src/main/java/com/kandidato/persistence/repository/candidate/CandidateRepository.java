package com.kandidato.persistence.repository.candidate;

import com.kandidato.persistence.entity.Candidate;
import com.kandidato.persistence.repository.candidate.query.CandidateQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>, com.kandidato.persistence.repository.base.Repository<Candidate, CandidateQuery> {
    //@Query("select v from Candidate v where v.creator.id = ?1")
    List<Candidate> findByCreatorId(Long creatorId);

    @Query("select c from Candidate c where c not in (select f.candidate from Flow f where f.vacancy.id = :vacancyId) and c.creator.id=:creatorId")
    List<Candidate> findNotConnectedToVacancy(@Param("vacancyId") Long vacancyId, @Param("creatorId") Long creatorId, Pageable pageable);

    @Query("select c from Candidate c where c.creator.id=:creatorId")
    List<Candidate> find(@Param("creatorId") Long creatorId, Pageable pageable);

    @Query(value = "select tf1.candidate  FROM TimelineField as tf1, TimelineField as tf2 where tf1.candidate.id=tf2.candidate.id and tf1.type.name='NAME_SURNAME' and tf1.fieldValue like concat('%',:name,'%') and tf2.type.name='EMAIL' and tf2.fieldValue like concat('%',:email,'%') and tf1.creator.id=:creatorId")
        //@Query(value = "SELECT c.* FROM TIMELINE_FIELD as tf1 join TIMELINE_FIELD_TYPE as tft1 on tft1.TIMELINE_FIELD_TYPE_ID=tf1.TIMELINE_FIELD_TYPE_ID join TIMELINE_FIELD as tf2 on tf1.CANDIDATE_ID=tf2.CANDIDATE_ID join TIMELINE_FIELD_TYPE as tft2 on tft2.TIMELINE_FIELD_TYPE_ID=tf2.TIMELINE_FIELD_TYPE_ID join CANDIDATES as c on c.CANDIDATE_ID=tf1.CANDIDATE_ID where tft1.LABEL='NAME_SURNAME' and tf1.FIELD_VALUE=:name and tft2.LABEL='EMAIL' and tf2.FIELD_VALUE=:email and tf1.USER_ID=:creatorId", nativeQuery = true)
    List<Candidate> findByNameAndMail(@Param("name") String name, @Param("email") String email, @Param("creatorId") Long creatorId, Pageable pageable);

    @Query(value = "select tf1.candidate  FROM TimelineField as tf1 where  tf1.type.name='NAME_SURNAME' and tf1.fieldValue like concat('%',:name,'%') and tf1.creator.id=:creatorId")
    List<Candidate> findByName(@Param("name") String name, @Param("creatorId") Long creatorId, Pageable pageable);

    @Query(value = "select tf2.candidate  FROM  TimelineField as tf2 where tf2.type.name='EMAIL' and tf2.fieldValue like concat('%',:email,'%') and tf2.creator.id=:creatorId")
    List<Candidate> findByMail(@Param("email") String email, @Param("creatorId") Long creatorId, Pageable pageable);

}
