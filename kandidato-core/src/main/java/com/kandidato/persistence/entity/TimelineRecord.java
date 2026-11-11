package com.kandidato.persistence.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimelineRecord implements Entity, CreatorAware {

    @Id
    @GeneratedValue
    @Column(name = "TIMELINE_RECORD_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "CANDIDATE_ID")
    private Candidate candidate;


    @OneToMany(mappedBy = "timelineRecord", cascade = CascadeType.ALL, targetEntity = TimelineField.class)
    private List<TimelineField> fields = new ArrayList<>();

    @Column(name = "CREATION_TIME")
    private Date createTime;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
    public List<TimelineField> getFields() {
        return fields;
    }

    public void setFields(List<TimelineField> fields) {
        this.fields = fields;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Override
    public User getCreator() {
        return this.creator;
    }

    @Override
    public void setCreator(User creator) {
        this.creator = creator;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
        builder.append(": {");
        builder.append("id: ");
        builder.append(id);
        builder.append(", candidate: ");
        builder.append(candidate.getId());
        builder.append(", fieldValue: ");
        builder.append(createTime);
        builder.append(", createTime: ");
        builder.append(", userId: ");
        builder.append(creator.getId());
        builder.append("}");
        return builder.toString();
    }
}
