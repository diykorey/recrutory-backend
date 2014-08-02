package com.kandidato.security.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Extension of the {@link User}, which holds user id from the database.
 * <p/>
 * Created by andriy on 7/27/14.
 */
public class KandidatoUser extends User {


    private final long id;

    /**
     * Returns user identifier.
     *
     * @return user identifier
     */
    public long getId() {
        return id;
    }

    public KandidatoUser(long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
    }
}
