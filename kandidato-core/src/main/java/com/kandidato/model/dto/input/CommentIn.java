package com.kandidato.model.dto.input;

import com.kandidato.constants.CommentType;

import javax.validation.constraints.NotNull;

public class CommentIn {
    private Long entityId;

    private CommentType type;

    @NotNull
    private String text;

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public CommentType getType() {
        return type;
    }

    public void setType(CommentType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "CommentIn{" +
                "id=" + entityId +
                ", type=" + type +
                ", text='" + text + '\'' +
                '}';
    }
}
