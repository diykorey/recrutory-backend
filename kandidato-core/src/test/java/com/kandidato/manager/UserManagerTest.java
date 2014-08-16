package com.kandidato.manager;

import com.google.common.collect.Iterables;
import com.kandidato.config.PersistenceConfig;
import com.kandidato.manager.user.UserManager;
import com.kandidato.manager.user.UserManagerImpl;
import com.kandidato.persistence.entity.User;
import com.kandidato.persistence.repository.user.UserRepository;
import com.kandidato.persistence.repository.user.query.UserByLoginQuery;
import com.kandidato.persistence.repository.user.query.UserQuery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.transaction.Transactional;

/**
 * Created by andriy on 8/2/14.
 */
@ContextConfiguration(classes = {PersistenceConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserManagerTest {

    private static final String USER_WORD = "user";
    public static final String LOGIN = "user2";

    @Autowired
    private UserRepository userRepo;

    private UserManager userManager;

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

//    @Before
    public void prepareData() {
        UserQuery uq = new UserByLoginQuery(LOGIN);
        User u = Iterables.getFirst(userRepo.query(uq), null);
        if (u != null) {
            userRepo.delete(u);
            userRepo.flush();
        }
    }

//    @Test
    public void shouldCreateUserWithHashedPassword() {
        userManager = new UserManagerImpl();
        ReflectionTestUtils.setField(userManager, "userRepository", userRepo);
        User user = new User(LOGIN, USER_WORD.getBytes(), "Test name", "Test surname");
        User result = userManager.createUser(user);

        Assert.assertTrue(encoder.matches(USER_WORD, new String(result.getPassword())));


    }
}
