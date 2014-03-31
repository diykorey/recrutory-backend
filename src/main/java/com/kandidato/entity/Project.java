package com.kandidato.entity;

import java.io.Serializable;
import java.util.Date;

public class Project implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String name;
  private String description;
  private Date createTime;

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
    builder.append("name: ");
    builder.append(name);
    builder.append(", description: ");
    builder.append(description);
    builder.append(", createTime: ");
    builder.append(createTime);
    builder.append("}");
    return builder.toString();
  }
}
