package com.kandidato.persistence.entity;

/**
 * Created by andriy on 6/4/14.
 */
public interface CreatorAware {

    User getCreator();

    void setCreator(User creator);

}
