package com.kandidato.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kandidato.constants.PredefinedTimelineFieldType;
import com.kandidato.persistence.entity.comment.CandidateComment;
import com.kandidato.persistence.entity.comment.CommentableEntity;
import com.kandidato.persistence.entity.tag.Tag;
import com.kandidato.persistence.entity.tag.Taggable;

import javax.persistence.*;
import java.util.*;


@javax.persistence.Entity
@Table(name = "CANDIDATES")
public class Candidate implements CommentableEntity<CandidateComment>, CreatorAware, Taggable {

    @Id
    @Column(name = "CANDIDATE_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "CREATION_TIME")
    private Date createTime = new Date();

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "CANDIDATE_TAGS", joinColumns = {@JoinColumn(name = "CANDIDATE_ID")}, inverseJoinColumns = {@JoinColumn(name = "TAG_ID")})
    @JsonIgnore
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL, targetEntity = CandidateComment.class)
    private List<CandidateComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, targetEntity = TimelineField.class, fetch = FetchType.LAZY)
    @OrderBy("createTime DESC")
    private List<TimelineField> timelineFields = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User creator;

    public Candidate() {
    }

    public Candidate(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Set<Tag> getTags() {
        return tags;
    }

    @Override
    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public List<CandidateComment> getComments() {
        return this.comments;
    }

    @Override
    public void setComments(List<CandidateComment> comments) {
        this.comments = comments;
    }

    @Override
    public User getCreator() {
        return this.creator;
    }

    @Override
    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<TimelineField> getTimelineFields() {
        return timelineFields;
    }

    public void setTimelineFields(List<TimelineField> timelineFields) {
        this.timelineFields = timelineFields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate)) return false;

        Candidate candidate = (Candidate) o;

        if (!createTime.equals(candidate.createTime)) return false;
        if (!tags.equals(candidate.tags)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = createTime.hashCode();
        result = 31 * result + tags.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
        builder.append(": {");
        builder.append("id: ");
        builder.append(id);
        builder.append(", createTime: ");
        builder.append(createTime);
        builder.append("}");
        return builder.toString();
    }
}
