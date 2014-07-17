package com.kandidato.persistence.repository.comment;

import com.kandidato.persistence.entity.comment.CommentableEntity;
import com.kandidato.persistence.entity.comment.EntityComment;
import com.kandidato.persistence.repository.query.Query;

/**
 * Created by andriy on 4/23/14.
 */
public interface CommentRepositoryCustom extends com.kandidato.persistence.repository.base.Repository<EntityComment, Query> {
    <T extends CommentableEntity<?>> void addComment(long authorId, long entityId, String commentText, Class<T> entityClass);
}
