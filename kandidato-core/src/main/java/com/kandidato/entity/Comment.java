package com.kandidato.entity;

import java.util.Date;

public class Comment implements Entity {

  private long id;
  private String content = "";
  private Date createTime = new Date();
  private User author;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
    builder.append(": {");
    builder.append("id: ");
    builder.append(id);
    builder.append(", content: ");
    builder.append(content);
    builder.append(", createTime: ");
    builder.append(createTime);
    builder.append("}");
    return builder.toString();
  }

}
