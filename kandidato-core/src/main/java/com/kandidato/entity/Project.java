package com.kandidato.entity;

import java.util.Date;

public class Project {

  private long id;
  private String name;
  private String description;
  private Date createTime;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
    builder.append(": {");
    builder.append("id: ");
    builder.append(id);
    builder.append(", name: ");
    builder.append(name);
    builder.append(", description: ");
    builder.append(description);
    builder.append(", createTime: ");
    builder.append(createTime);
    builder.append("}");
    return builder.toString();
  }
}
