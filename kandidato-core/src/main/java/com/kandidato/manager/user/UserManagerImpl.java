package com.kandidato.manager.user;

import com.google.common.collect.Iterables;
import com.kandidato.persistence.entity.User;
import com.kandidato.persistence.repository.user.UserRepository;
import com.kandidato.persistence.repository.user.query.UserByLoginQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by andriy on 7/27/14.
 */
@Service
public class UserManagerImpl implements UserManager {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;


    @Override
    public User getUserByLogin(String login) {
        return Iterables.getFirst(this.userRepository.query(new UserByLoginQuery(login)), null);
    }

    @Override
    public User createUser(User user) {
        CharSequence seq = new String(user.getPassword());
        user.setPassword(passwordEncoder.encode(seq).getBytes());
        return userRepository.saveAndFlush(user);
    }
}
