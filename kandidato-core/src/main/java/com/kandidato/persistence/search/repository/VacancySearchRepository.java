package com.kandidato.persistence.search.repository;

import com.kandidato.persistence.search.model.VacancyDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancySearchRepository extends ElasticsearchRepository<VacancyDocument, String> {
}
