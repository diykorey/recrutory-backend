package com.kandidato.constants;

import com.kandidato.persistence.entity.FlowAction;
import com.kandidato.persistence.entity.Person;
import com.kandidato.persistence.entity.Resume;
import com.kandidato.persistence.entity.Vacancy;
import com.kandidato.persistence.entity.comment.*;

/**
 * Created by andriy on 4/26/14.
 */
public enum CommentType {

    VACANCY(Vacancy.class, VacancyComment.DISCRIMINATOR), RESUME(Resume.class, ResumeComment.DISCRIMINATOR), PERSON(Person.class, PersonComment.DISCRIMINATOR), ACTION(FlowAction.class, ActionComment.DISCRIMINATOR);

    private final Class<? extends CommentableEntity<?>> clazz;

    public final String discriminator;

    public Class<? extends CommentableEntity<?>> getClazz() {
        return clazz;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    private CommentType(Class<? extends CommentableEntity<?>> clazz, String discriminator) {
        this.clazz = clazz;
        this.discriminator = discriminator;
    }
}
