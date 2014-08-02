package com.kandidato.persistence.repository.user.query;

import com.kandidato.persistence.entity.User;
import com.kandidato.persistence.repository.query.AbstractCriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Created by andriy on 7/27/14.
 */
public class UserByLoginQuery extends AbstractCriteriaQuery implements UserQuery {

    private final String login;

    public UserByLoginQuery(String login) {
        super(User.class);
        this.login = login;
    }

    @Override
    public Criterion toCriterion() {
        return Restrictions.eq("login", this.login);
    }
}
