package com.kandidato.persistence.entity;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "TIMELINE_FIELD_TYPE")
public class TimelineFieldType implements Entity, CreatorAware, Comparable<TimelineFieldType> {

    @Id
    @GeneratedValue
    @Column(name = "TIMELINE_FIELD_TYPE_ID")
    private Long id;

    @Column(name = "LABEL")
    private String name;

    @Column(name = "PRIORITY")
    private int priority = 1;

    @Column(name = "PRIORITY_GROUP")
    private int priorityGroup = 3;

    @Column(name = "COUNTERPART")
    private boolean counterpart;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User creator;

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

    public boolean isCounterpart() {
        return counterpart;
    }


    public void setCounterpart(boolean counterpart) {
        this.counterpart = counterpart;
    }

    @Override
    public User getCreator() {
        return this.creator;
    }

    @Override
    public void setCreator(User creator) {
        this.creator = creator;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriorityGroup() {
        return priorityGroup;
    }

    public void setPriorityGroup(int priorityGroup) {
        this.priorityGroup = priorityGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimelineFieldType)) return false;

        TimelineFieldType that = (TimelineFieldType) o;

        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TimelineFieldType{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", priority=").append(priority);
        sb.append(", priorityGroup=").append(priorityGroup);
        sb.append(", counterpart=").append(counterpart);
        sb.append(", creator=").append(creator);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(TimelineFieldType type) {
        if (this.priorityGroup != type.priorityGroup) {
            return this.priorityGroup - type.priorityGroup;
        }
        if (this.priority != type.priority) {
            return this.priority - type.priority;
        }
        return this.name.compareTo(type.name);
    }
}
