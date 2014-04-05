package com.kandidato.repository.search;

import com.kandidato.repository.search.elastic.ResumeDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ResumeSearchRepository extends ElasticsearchRepository<ResumeDocument, String> {
}
