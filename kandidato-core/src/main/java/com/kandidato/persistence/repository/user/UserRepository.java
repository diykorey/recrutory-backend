package com.kandidato.persistence.repository.user;

import com.kandidato.persistence.entity.User;
import com.kandidato.persistence.repository.base.Repository;
import com.kandidato.persistence.repository.user.query.UserQuery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User repo.
 * <p/>
 * Created by andriy on 7/27/14.
 */
@org.springframework.stereotype.Repository
public interface UserRepository extends JpaRepository<User, Long>, Repository<User, UserQuery> {
}
