package com.kandidato.entity;

import java.util.Date;

import com.kandidato.constants.FlowState;

public class FlowAction implements Entity {

  private long id;
  private FlowState state;
  private String description;
  private Date createTime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public FlowState getState() {
    return state;
  }

  public void setState(FlowState state) {
    this.state = state;
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
    builder.append(", createTime: ");
    builder.append(createTime);
    builder.append(", state: ");
    builder.append(state);
    builder.append(", description: ");
    builder.append(description);
    builder.append("}");
    return builder.toString();
  }
}
