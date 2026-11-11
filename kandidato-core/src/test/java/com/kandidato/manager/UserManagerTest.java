package com.kandidato.manager;

import com.kandidato.manager.user.UserManager;
import com.kandidato.persistence.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by andriy on 8/2/14.
 */
//@ContextConfiguration(classes = {PersistenceConfig.class})
//@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
public class UserManagerTest {

    private static final String USER_WORD = "user";
    public static final String LOGIN = "user2";

    @Autowired
    private UserRepository userRepo;

    private UserManager userManager;

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

//    @Before
//    public void prepareData() {
//        UserQuery uq = new UserByLoginQuery(LOGIN);
//        UserDTO u = Iterables.getFirst(userRepo.query(uq), null);
//        if (u != null) {
//            userRepo.delete(u);
//            userRepo.flush();
//        }
//    }

//    @Test
    public void shouldCreateUserWithHashedPassword() {
      //  userManager = new UserManagerImpl();
      //  ReflectionTestUtils.setField(userManager, "userRepository", userRepo);
      //  UserDTO user = new UserDTO(LOGIN, USER_WORD.getBytes(), "Test name", "Test surname");
       // UserDTO result = userManager.createUser(user);

      //  Assert.assertTrue(encoder.matches(USER_WORD, new String(result.getPassword())));
    }
}
