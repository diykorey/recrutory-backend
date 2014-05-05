package com.kandidato.manager.comment;

import com.kandidato.constants.CommentType;
import com.kandidato.persistence.entity.comment.EntityComment;

import java.util.List;

/**
 * Created by andriy on 4/23/14.
 */
public interface CommentManager {

    void addComment(long authorId, long entityId, CommentType type, String commentText);

    List<EntityComment<?>> getEntityComments(long entityId, CommentType type);

}
