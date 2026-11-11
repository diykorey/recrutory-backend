package com.kandidato.model.dto;

import javax.validation.constraints.NotNull;

/**
 * Dto for tags.
 * <p/>
 * Created by andriy on 4/6/14.
 */
public class TagDTO implements Dto {

    private Long id;

    @NotNull
    private String keyword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagDTO)) return false;

        TagDTO tag = (TagDTO) o;

        if (!keyword.equals(tag.keyword)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return keyword.hashCode();
    }

    @Override
    public String toString() {
        return "TagDTO{" +
                "id=" + id +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
