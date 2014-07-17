package com.kandidato.persistence.repository.comment;

import com.kandidato.persistence.entity.*;
import com.kandidato.persistence.entity.comment.*;
import com.kandidato.persistence.repository.base.HibernateRepository;
import com.kandidato.persistence.repository.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.Date;

/**
 * Created by andriy on 4/24/14.
 */
public class CommentRepositoryImpl extends HibernateRepository<EntityComment, Query> implements CommentRepositoryCustom {

    @Autowired
    public CommentRepositoryImpl(EntityManager entityManager) {
        super(entityManager, EntityComment.class);
    }

    @Override
    public <T extends CommentableEntity<?>> void addComment(long authorId, long entityId, String commentText, Class<T> entityClass) {

        try {
            T entity = this.entityManager.getReference(entityClass, entityId);
            User u = this.entityManager.getReference(User.class, authorId);
            EntityComment comment;
            if (Vacancy.class.equals(entityClass)) {
                comment = new VacancyComment(commentText, new Date(), u, (Vacancy) entity);
            } else if (FlowAction.class.equals(entityClass)) {
                comment = new ActionComment(commentText, new Date(), u, (FlowAction) entity);
            } else if (Candidate.class.equals(entityClass)) {
                comment = new CandidateComment(commentText, new Date(), u, (Candidate) entity);
            } else if (Resume.class.equals(entityClass)) {
                comment = new ActionComment(commentText, new Date(), u, (FlowAction) entity);
            } else {
                throw new IllegalArgumentException("Unknown commentable entity");
            }
            this.entityManager.persist(comment);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
