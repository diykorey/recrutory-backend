package com.kandidato.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Flow implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Person person;
  private Vacancy vacancy;
  private List<FlowAction> actions = new ArrayList<>();
  private Date createTime;
  private boolean active = true;

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public Vacancy getVacancy() {
    return vacancy;
  }

  public void setVacancy(Vacancy vacancy) {
    this.vacancy = vacancy;
  }

  public List<FlowAction> getActions() {
    return actions;
  }

  public void setActions(List<FlowAction> actions) {
    this.actions = actions;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
    builder.append(": {");
    builder.append("createTime: ");
    builder.append(createTime);
    builder.append(", active: ");
    builder.append(active);
    builder.append("}");
    return builder.toString();
  }
}
