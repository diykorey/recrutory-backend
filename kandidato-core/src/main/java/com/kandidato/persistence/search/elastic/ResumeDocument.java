package com.kandidato.persistence.search.elastic;


import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "resumes", type = "resume")
public class ResumeDocument {

    private long organizationId;
    private long userId;
    private String text;
    private Long id;


    public ResumeDocument(Long id, long organizationId, long userId, String text) {
        this.organizationId = organizationId;
        this.userId = userId;
        this.text = text;
        this.id = id;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public long getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }
}
