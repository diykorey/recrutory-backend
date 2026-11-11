package com.kandidato.persistence.entity;

import com.kandidato.util.KandidatoUtil;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@javax.persistence.Entity
@Table(name = "TIMELINE_FIELD")
public class TimelineField implements Entity, CreatorAware, Comparable<TimelineField> {


    @Id
    @GeneratedValue
    @Column(name = "TIMELINE_FIELD_ID")
    private Long id;

    @Column(name = "FIELD_UUID")
    private UUID uuid;

    @Column(name = "FIELD_NAME")
    private String name;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "TIMELINE_FIELD_TYPE_ID")
    private TimelineFieldType type;

    @Column(name = "FIELD_VALUE")
    private String fieldValue;

    @ManyToOne
    @JoinColumn(name = "CANDIDATE_ID")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User creator;

    @Column(name = "CREATION_TIME")
    private Date createTime;

    @Column(name = "PARENT_FIELD_UUID")
    private UUID parentUuid;

    public TimelineField() {
        UUID uuid = UUID.randomUUID();
        this.uuid = uuid;
        this.parentUuid = uuid;
        this.createTime = KandidatoUtil.getToday();
    }

    public TimelineField(UUID parentUuid) {
        UUID uuid = UUID.randomUUID();
        this.uuid = uuid;
        this.parentUuid = parentUuid;
        this.createTime = KandidatoUtil.getToday();
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TimelineFieldType getType() {
        return type;
    }

    public void setType(TimelineFieldType type) {
        this.type = type;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public User getCreator() {
        return this.creator;
    }

    @Override
    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getParentUuid() {
        return parentUuid;
    }

    public void setParentUuid(UUID parentUuid) {
        this.parentUuid = parentUuid;
    }

    @Override
    public int compareTo(TimelineField field) {
        if (!this.createTime.equals(field.createTime)) {
            return field.createTime.compareTo(this.createTime);
        }
        if (!this.type.equals(field.type)) {
            return this.type.compareTo(field.type);
        }
        return this.parentUuid.compareTo(field.parentUuid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimelineField)) return false;

        TimelineField that = (TimelineField) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TimelineField{");
        sb.append("id=").append(id);
        sb.append(", uuid=").append(uuid);
        sb.append(", name='").append(name).append('\'');
        sb.append(", type=").append(type);
        sb.append(", fieldValue='").append(fieldValue).append('\'');
        sb.append(", candidate=").append(candidate);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", parentUuid=").append(parentUuid);
        sb.append('}');
        return sb.toString();
    }
}
