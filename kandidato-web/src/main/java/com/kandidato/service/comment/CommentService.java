package com.kandidato.service.comment;

import com.kandidato.constants.CommentType;

/**
 * Created by andriy on 4/26/14.
 */
public interface CommentService {

    void addComment(long authorId, long entityId, CommentType type, String comment);

}
