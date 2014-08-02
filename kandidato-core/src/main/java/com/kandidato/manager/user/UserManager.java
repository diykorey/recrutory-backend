package com.kandidato.manager.user;

import com.kandidato.persistence.entity.User;

/**
 * Created by andriy on 7/27/14.
 */
public interface UserManager {

    /**
     * Retrieves the {@link User} with the corresponding login.
     *
     * @param login
     * @return user or {@code null} if the user is not found
     */
    User getUserByLogin(String login);

    /**
     * Creates a new user.
     *
     * @param user to be created
     * @return instance of the user, which was created
     */
    User createUser(User user);

}
