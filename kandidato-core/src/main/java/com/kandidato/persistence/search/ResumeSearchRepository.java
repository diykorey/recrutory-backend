package com.kandidato.persistence.search;

import com.kandidato.persistence.search.elastic.ResumeDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ResumeSearchRepository extends ElasticsearchRepository<ResumeDocument, Long> {
}
