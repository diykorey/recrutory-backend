package com.kandidato.persistence.repository.search.elastic;


import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "", type = "")
public class ResumeDocument {

    private long organizationId;
    private long userId;
    private String text;


    public ResumeDocument(long organizationId, long userId, String text) {
        this.organizationId = organizationId;
        this.userId = userId;
        this.text = text;
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
}
