package com.kandidato.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kandidato.constants.VacancyState;

public class Vacancy implements Entity {

  private long id;

  private VacancyState state;
  private boolean hot;
  private String requirements;
  private Project project;
  private List<Flow> flows = new ArrayList<>();
  private List<Comment> comments = new ArrayList<>();
  private User creator;
  private Date createTime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public VacancyState getState() {
    return state;
  }

  public void setState(VacancyState state) {
    this.state = state;
  }

  public boolean isHot() {
    return hot;
  }

  public void setHot(boolean hot) {
    this.hot = hot;
  }

  public String getRequirements() {
    return requirements;
  }

  public void setRequirements(String requirements) {
    this.requirements = requirements;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public List<Flow> getFlows() {
    return flows;
  }

  public void setFlows(List<Flow> flows) {
    this.flows = flows;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public User getCreator() {
    return creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
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
    builder.append(", state: ");
    builder.append(state);
    builder.append(", hot: ");
    builder.append(hot);
    builder.append(", requirements: ");
    builder.append(requirements);
    builder.append("}");
    return builder.toString();
  }
}
