package com.kandidato.persistence.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@javax.persistence.Entity
@Table(name = "PEOPLE")
public class Person implements Entity {

    @Id
    @Column(name = "PERSON_ID")
    @GeneratedValue
    private long id;

    @Column(name = "FIRST_NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "CREATION_TIME")
    private Date createTime;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "PEOPLE_TAGS", joinColumns = {@JoinColumn(name = "PERSON_ID")}, inverseJoinColumns = {@JoinColumn(name = "TAG_ID")})
    private Set<Tag> tags;

//    private List<TimelineRecord> timeline;

//    private List<Contact> contacts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
