package com.kandidato.persistence.search;

import com.kandidato.persistence.search.elastic.ResumeDocument;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.support.AbstractElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.stereotype.Component;

@Component
public class ResumeSearchRepositoryImpl extends AbstractElasticsearchRepository<ResumeDocument, Long> implements ResumeSearchRepository  {

    public ResumeSearchRepositoryImpl() {
    }

    public ResumeSearchRepositoryImpl(ElasticsearchOperations elasticsearchOperations) {
        super(elasticsearchOperations);
    }

    public ResumeSearchRepositoryImpl(ElasticsearchEntityInformation<ResumeDocument, Long> metadata, ElasticsearchOperations elasticsearchOperations) {
        super(metadata, elasticsearchOperations);
    }

    @Override
    protected String stringIdRepresentation(Long id) {
        return Long.toString(id);
    }
}
