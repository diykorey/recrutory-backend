package com.kandidato.persistence.search.elastic;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "resumes", type = "resume")
public class ResumeDocument {

    @Id
    private Long id;

    private long organizationId;
    private long userId;
    private long candidateId;
    private String text;

    public ResumeDocument() {
    }

    public ResumeDocument(Long id, long candidateId, String text) {
        this.organizationId = 0;
        this.userId = 0;
        this.text = text;
        this.id = id;
        this.candidateId = candidateId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(long candidateId) {
        this.candidateId = candidateId;
    }

    @Override
    public String toString() {
        return "ResumeDocument{" + "id=" + id +
        ", text='" + text + '\'' + ", candidateId=" + candidateId +'}';
    }
}
