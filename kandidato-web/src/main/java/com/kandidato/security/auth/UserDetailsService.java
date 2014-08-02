package com.kandidato.security.auth;

import com.kandidato.manager.user.UserManager;
import com.kandidato.persistence.entity.User;
import com.kandidato.security.model.KandidatoUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Implementation of {@link UserDetailsService}, based on {@link com.kandidato.manager.user.UserManager}.
 * <p/>
 * Created by andriy on 7/27/14.
 */
@Service
@Transactional(readOnly = true)
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = this.userManager.getUserByLogin(login);
        if (null == user) {
            LOG.debug("User not found for login {}", login);
            throw new UsernameNotFoundException(login);
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>(1);
        //TODO Replace hardcoded roles by values from the database
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new KandidatoUser(user.getId(), user.getLogin(), new String(user.getPassword()), authorities);
    }
}
