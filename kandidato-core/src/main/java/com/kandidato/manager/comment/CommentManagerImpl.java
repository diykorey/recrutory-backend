package com.kandidato.manager.comment;

import com.kandidato.constants.CommentType;
import com.kandidato.persistence.repository.comment.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andriy on 4/23/14.
 */
@Component
public class CommentManagerImpl implements CommentManager {

    @Autowired
    private CommentRepository repository;

    @Override
    public void addComment(long authorId, long entityId, CommentType type, String commentText) {
        this.repository.addComment(authorId, entityId, commentText, type.getClazz());
    }
}
