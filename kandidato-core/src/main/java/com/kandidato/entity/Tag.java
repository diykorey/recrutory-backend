package com.kandidato.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity for tags.
 *
 * Created by andriy on 4/6/14.
 */
@javax.persistence.Entity
@Table(name="TAGS")
public class Tag implements  Entity {

    @Id
    @GeneratedValue
    @Column(name="TAG_ID")
    private Long id;

    @Column(name = "KEYWORD")
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
