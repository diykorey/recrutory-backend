package com.kandidato.persistence.entity;


import com.kandidato.persistence.entity.comment.CommentableEntity;
import com.kandidato.persistence.entity.comment.ResumeComment;

import com.kandidato.constants.SearchIndexState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "RESUME")
public class Resume implements CommentableEntity<ResumeComment>, CreatorAware {

    @Id
    @Column(name = "RESUME_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private SearchIndexState state;

    @ManyToOne
    @JoinColumn(name = "CANDIDATE_ID")
    private Candidate candidate;

    @Column(name = "DATA_URL")
    private String dataUrl;

    @Column(name = "ORIGINAL_FILE_NAME")
    private String originalFileName;

    //TODO do we need it?
    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL, targetEntity = ResumeComment.class)
    private List<ResumeComment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public List<ResumeComment> getComments() {
        return comments;
    }

    @Override
    public void setComments(List<ResumeComment> comments) {
        this.comments = comments;
    }

    public SearchIndexState getState() {
        return state;
    }

    public void setState(SearchIndexState state) {
        this.state = state;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @Override
    public User getCreator() {
        return this.creator;
    }

    @Override
    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }
}
