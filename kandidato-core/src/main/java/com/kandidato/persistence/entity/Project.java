package com.kandidato.persistence.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@javax.persistence.Entity
@Table(name = "PROJECTS")
public class Project implements Entity {


    @Id
    @GeneratedValue
    @Column(name = "PROJECT_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATION_TIME")
    private Date creationTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date createTime) {
        this.creationTime = createTime;
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
        builder.append(", creationTime: ");
        builder.append(creationTime);
        builder.append("}");
        return builder.toString();
    }
}
