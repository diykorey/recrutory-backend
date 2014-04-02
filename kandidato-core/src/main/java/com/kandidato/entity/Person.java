package com.kandidato.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class Person {

  private long id;
  private String name;
  private Date createTime;
  private Set<String> tags;
  private List<TimelineRecord> timeline;
  private List<Contact> contacts;

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

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Set<String> getTags() {
    return tags;
  }

  public void setTags(Set<String> tags) {
    this.tags = tags;
  }

  public List<TimelineRecord> getTimeline() {
    return timeline;
  }

  public void setTimeline(List<TimelineRecord> timeline) {
    this.timeline = timeline;
  }

  public List<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

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
