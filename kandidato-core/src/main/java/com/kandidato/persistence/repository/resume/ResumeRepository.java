package com.kandidato.persistence.repository.resume;


import com.kandidato.persistence.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
