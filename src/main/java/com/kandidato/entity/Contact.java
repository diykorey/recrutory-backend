package com.kandidato.entity;

import com.kandidato.constants.ContactType;

public class Contact {

  private long id;
  private ContactType type;
  private String value;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public ContactType getType() {
    return type;
  }

  public void setType(ContactType type) {
    this.type = type;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
    builder.append(": {");
    builder.append("id: ");
    builder.append(id);
    builder.append(", type: ");
    builder.append(type);
    builder.append(", value: ");
    builder.append(value);
    builder.append("}");
    return builder.toString();
  }
}
