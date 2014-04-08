package com.kandidato.dto;

import java.util.Date;

public class CommentDto implements Dto {

    private Long id;
    private String content = "";
    private Date createTime = new Date();
    private UserDto author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public UserDto getAuthor() {
        return author;
    }

    public void setAuthor(UserDto author) {
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
