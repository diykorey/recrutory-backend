package com.kandidato.persistence.entity;

import com.kandidato.constants.ContactType;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "CONTACTS")
public class Contact implements Entity {

    @Id
    @GeneratedValue
    @Column(name = "CONTACT_ID")
    private Long id;

    @Column(name = "CONTACT_TYPE")
    @Enumerated(EnumType.STRING)
    private ContactType type;

    @Column(name = "VALUE")
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
