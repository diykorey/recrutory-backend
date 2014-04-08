package com.kandidato.dto;

/**
 * Entity for tags.
 * <p/>
 * Created by andriy on 4/6/14.
 */
public class TagDto implements Dto {

    private Long id;

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
}
