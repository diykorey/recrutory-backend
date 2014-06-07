package com.kandidato.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kandidato.persistence.entity.comment.CommentableEntity;
import com.kandidato.persistence.entity.comment.ResumeComment;

import com.kandidato.constants.ResumeState;

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
    private ResumeState state;

    @ManyToOne
    @JoinColumn(name = "CANDIDATE_ID")
    private Candidate candidate;

    @Column(name = "DATA")
    @Lob
    private byte[] data;

    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL)
    @JsonIgnore
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public List<ResumeComment> getComments() {
        return comments;
    }

    @Override
    public void setComments(List<ResumeComment> comments) {
        this.comments = comments;
    }

    public ResumeState getState() {
        return state;
    }

    public void setState(ResumeState state) {
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
}
