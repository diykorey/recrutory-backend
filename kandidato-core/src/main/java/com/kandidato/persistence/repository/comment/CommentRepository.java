package com.kandidato.persistence.repository.comment;

import com.kandidato.persistence.entity.comment.EntityComment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for comments.
 *
 * Created by andriy on 4/23/14.
 */
@org.springframework.stereotype.Repository
public interface CommentRepository extends CommentRepositoryCustom, JpaRepository<EntityComment, Long> {
}
