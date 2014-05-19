package com.kandidato.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kandidato.persistence.entity.comment.CommentableEntity;
import com.kandidato.persistence.entity.comment.CandidateComment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


@javax.persistence.Entity
@Table(name = "CANDIDATES")
public class Candidate implements CommentableEntity<CandidateComment> {

    @Id
    @Column(name = "CANDIDATE_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "FIRST_NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "CREATION_TIME")
    private Date createTime;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "CANDIDATE_TAGS", joinColumns = {@JoinColumn(name = "CANDIDATE_ID")}, inverseJoinColumns = {@JoinColumn(name = "TAG_ID")})
    @JsonIgnore
    private Set<Tag> tags;

    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CandidateComment> comments = new ArrayList<>();

//    private List<TimelineRecord> timeline;

//    private List<Contact> contacts;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Set<Tag> getTags() {
        return tags;
    }

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

    //    public List<TimelineRecord> getTimeline() {
//        return timeline;
//    }
//
//    public void setTimeline(List<TimelineRecord> timeline) {
//        this.timeline = timeline;
//    }

//    public List<Contact> getContacts() {
//        return contacts;
//    }
//
//    public void setContacts(List<Contact> contacts) {
//        this.contacts = contacts;
//    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
        builder.append(": {");
        builder.append("id: ");
        builder.append(id);
        builder.append(", name: ");
        builder.append(name);
        builder.append(", createTime: ");
        builder.append(createTime);
        builder.append("}");
        return builder.toString();
    }
}
