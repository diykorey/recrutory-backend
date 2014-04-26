package com.kandidato.persistence.entity.comment;

import com.kandidato.persistence.entity.Entity;

import java.util.List;

/**
 * Interface for entities on which a comment can be placed.
 *
 * Created by andriy on 4/24/14.
 */
public interface CommentableEntity<T extends EntityComment> extends Entity {

    /**
     * Returns a list of comments placed on the entity by all the users.
     *
     * @return list of comments or an empty list, if no comments were left
     */
    List<T> getComments();

    /**
     * Sets the comments to the entity.
     *
     * @param comments
     */
    void setComments(List<T> comments);
}
