package com.kandidato.manager.comment;

import com.kandidato.constants.CommentType;

/**
 * Created by andriy on 4/23/14.
 */
public interface CommentManager {

    void addComment(long authorId, long entityId, CommentType type, String commentText);

}
