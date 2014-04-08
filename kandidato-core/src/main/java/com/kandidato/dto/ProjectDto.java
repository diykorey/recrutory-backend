package com.kandidato.dto;

import java.util.Date;

public class ProjectDto implements Dto {
    private Long id;

    private String name;

    private String description;

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
