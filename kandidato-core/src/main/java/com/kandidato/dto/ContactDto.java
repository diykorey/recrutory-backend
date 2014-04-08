package com.kandidato.dto;

import com.kandidato.constants.ContactType;

public class ContactDto implements com.kandidato.dto.Dto {

    private Long id;

    private ContactType type;

    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
