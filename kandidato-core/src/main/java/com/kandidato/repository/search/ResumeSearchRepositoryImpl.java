package com.kandidato.repository.search;

import com.kandidato.repository.search.elastic.ResumeDocument;
import org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public class ResumeSearchRepositoryImpl extends SimpleElasticsearchRepository<ResumeDocument> implements ResumeSearchRepository  {
}
