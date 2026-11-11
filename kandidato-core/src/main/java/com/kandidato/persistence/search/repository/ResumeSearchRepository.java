package com.kandidato.persistence.search.repository;

import com.kandidato.persistence.search.model.ResumeDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeSearchRepository extends ElasticsearchRepository<ResumeDocument, String> {
}
