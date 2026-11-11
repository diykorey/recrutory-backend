package com.kandidato.persistence.repository.resume;


import com.kandidato.constants.SearchIndexState;
import com.kandidato.persistence.entity.Resume;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByStateIs(SearchIndexState state, Pageable page);
}
