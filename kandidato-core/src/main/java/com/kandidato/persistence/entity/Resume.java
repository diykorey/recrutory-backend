package com.kandidato.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kandidato.persistence.entity.comment.CommentableEntity;
import com.kandidato.persistence.entity.comment.ResumeComment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "RESUME")
public class Resume implements CommentableEntity<ResumeComment> {

    @Id
    @Column(name = "RESUME_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "RESUME_DATA")
    @Lob
    private byte[] data;

    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ResumeComment> comments = new ArrayList<>();

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
}
