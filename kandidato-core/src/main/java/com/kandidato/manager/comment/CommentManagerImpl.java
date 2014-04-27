package com.kandidato.manager.comment;

import com.kandidato.constants.CommentType;
import com.kandidato.persistence.entity.comment.EntityComment;
import com.kandidato.persistence.repository.comment.CommentRepository;
import com.kandidato.persistence.repository.comment.query.CommentByEntityQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @Override
    public List<EntityComment<?>> getEntityComments(long entityId, CommentType type) {
        return new ArrayList(repository.query(new CommentByEntityQuery(entityId, type)));
    }
}
