package com.kandidato.entity;

import java.io.Serializable;

import com.kandidato.constants.ContactType;

public class Contact implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private ContactType type;
  private String value;

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
    builder.append("type: ");
    builder.append(type);
    builder.append(", value: ");
    builder.append(value);
    builder.append("}");
    return builder.toString();
  }
}
