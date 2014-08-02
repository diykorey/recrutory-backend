package com.kandidato.persistence.repository.user;

import com.kandidato.persistence.entity.User;
import com.kandidato.persistence.repository.base.HibernateRepository;
import com.kandidato.persistence.repository.user.query.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * Created by andriy on 7/27/14.
 */
public class UserRepositoryImpl extends HibernateRepository<User, UserQuery> implements UserRepositoryCustom {

    @Autowired
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager, User.class);
    }
}
