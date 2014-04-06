package com.kandidato.repository.resume;


import com.kandidato.entity.Resume;
import com.kandidato.repository.base.Repository;
import com.kandidato.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends Repository<Resume, Query>, JpaRepository<Resume, Long> {
}
