package com.kandidato.constants;

import com.kandidato.persistence.entity.FlowAction;
import com.kandidato.persistence.entity.Person;
import com.kandidato.persistence.entity.Resume;
import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.persistence.entity.comment.CommentableEntity;

/**
 * Created by andriy on 4/26/14.
 */
public enum CommentType {

    VACANCY(Vacancy.class), RESUME(Resume.class), PERSON(Person.class), ACTION(FlowAction.class);

    private final Class<? extends CommentableEntity<?>> clazz;

    public Class<? extends CommentableEntity<?>> getClazz() {
        return clazz;
    }

    private CommentType(Class<? extends CommentableEntity<?>> clazz) {
        this.clazz = clazz;
    }
}
