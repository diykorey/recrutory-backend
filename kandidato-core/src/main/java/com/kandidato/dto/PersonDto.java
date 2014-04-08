package com.kandidato.dto;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;
import java.util.Set;


public class PersonDto implements Dto {

    private Long id;

    private String name;

    private String lastName;

    private Date createTime;

    private Set<TagDto> tags;

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

    public Set<TagDto> getTags() {
        return tags;
    }

    public void setTags(Set<TagDto> tags) {
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
