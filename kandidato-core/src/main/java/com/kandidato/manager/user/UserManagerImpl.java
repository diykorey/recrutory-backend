package com.kandidato.manager.user;

import com.kandidato.model.dto.BaseUserDTO;
import com.kandidato.model.dto.TokenDTO;
import com.kandidato.model.dto.UserDTO;
import com.kandidato.persistence.entity.User;
import com.kandidato.persistence.repository.user.UserRepository;
import com.kandidato.security.model.CurrentProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UserManagerImpl implements UserManager {
    private static final Logger log = LoggerFactory.getLogger(UserManagerImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FacebookManager facebookManager;

    @Autowired
    private GoogleManager googleManager;

    @Autowired
    private LinkedInManager linkedInManager;

    private final Map<String, Function<String, CurrentProfile>> handlers = new HashMap<>();

    @PostConstruct
    private void init() {
        handlers.put(FACEBOOK, (String token) -> {
            log.debug("Run FB profileHandler for token: {}", token);
            org.springframework.social.facebook.api.User fbUser = facebookManager.getProfile(token);
            log.debug("Received social profile: {} by token: {}", fbUser, token);

            User user = userRepository.findByFacebookId(fbUser.getId());
            log.debug("Fetch user: {} by id: {}", user, fbUser.getId());
            if (user == null) {
                log.debug("Saved user with  id: {}", fbUser.getId());
                user = userRepository.save(User.createFbUser(fbUser.getId(), fbUser.getEmail(), fbUser.getFirstName(), fbUser.getLastName()));
                log.debug("Saved user: {} by id: {}", user, fbUser.getId());
            }

            return new CurrentProfile(user, fbUser);
        });

        handlers.put(GOOGLE, (String token) -> {
            log.debug("Run G+ profileHandler for token: {}", token);
            Person person = googleManager.getProfile(token);
            log.debug("Received social profile: {} by token: {}", person, token);
            User user = userRepository.findByGooglePlusId(person.getId());
            log.debug("Fetch user: {} by id: {}", user, person.getId());

            if (user == null) {
                log.debug("Saved user with  id: {}", person.getId());
                user = userRepository.save(User.createGoogleUser(person.getId(), person.getAccountEmail(), person.getGivenName(), person.getFamilyName()));
                log.debug("Saved user: {} by id: {}", user, person.getId());
            }

            return new CurrentProfile(user, person);
        });

        handlers.put(LINKEDIN, (String token) -> {
            log.debug("Run Li profileHandler for token: {}", token);
            LinkedInProfile profile = linkedInManager.getProfile(token);
            log.debug("Received social profile: {} by token: {}", toString(profile), token);

            User user = userRepository.findByLinkedinId(profile.getId());
            log.debug("Fetch user: {} by id: {}", user, profile.getId());

            if (user == null) {
                log.debug("Saved user with  id: {}", profile.getId());
                user = userRepository.save(User.createLinkedinUser(profile.getId(), profile.getEmailAddress(), profile.getFirstName(), profile.getLastName()));
                log.debug("Saved user: {} by id: {}", user, profile.getId());
            }

            return new CurrentProfile(user, profile);
        });
    }


    @Override
    public UserDTO attachNewProvider(CurrentProfile currentUser, TokenDTO token) {
        addProfileId(token, currentUser.getUser());
        return null;
    }

    @Override
    public Optional<CurrentProfile> getCurrentProfile(String provider, String token) {
        return Optional.of(new CurrentProfile(new User(1L), "linkedIn", "email", "name", "surname"));
        /*
        if (!handlers.containsKey(provider)) {
            log.debug("There is no handler for provider: {} Available handlers: {}", provider, handlers);
            return Optional.empty();
        }
        log.debug("Looking for profileHandler: {}", provider);
        Function<String, CurrentProfile> profileHandler = handlers.get(provider);
        log.debug("Found profileHandler: {}", profileHandler);

        log.debug("Profile: {}", profileHandler.apply(token));
        return Optional.of(profileHandler.apply(token));
*/
        //  return Optional.of(new CurrentProfile(new User(10L), "linkedIn", "email", "name", "surname"));
    }

    private User addProfileId(TokenDTO tokenDto, User user) {
        String token = tokenDto.getToken();
        switch (tokenDto.getProvider()) {
            case FACEBOOK:
                user.setFacebookId(getFacebookId(token));
            case GOOGLE:
                user.setGooglePlusId(getGoogleId(token));
            case LINKEDIN:
                user.setLinkedinId(getLinkedinId(token));
        }
        return userRepository.save(user);
    }

    private String getFacebookId(String token) {
        return facebookManager.getProfile(token).getId();
    }

    private String getGoogleId(String token) {
        return googleManager.getProfile(token).getId();
    }

    private String getLinkedinId(String token) {
        return linkedInManager.getProfile(token).getId();
    }


    private String toString(LinkedInProfile profile) {
        StringBuilder result = new StringBuilder("Linkedin profile: [");
        result.append("Id value: '").append(profile.getId()).append("', ");
        result.append("FirstName value: '").append(profile.getFirstName()).append("' ");
        result.append("EmailAddress value: '").append(profile.getEmailAddress()).append("', ");
        result.append("LastName value: '").append(profile.getLastName()).append("', ");
        if (profile.getConnectionAuthorization() != null) {
            result.append("ConnectionAuthorization name: '").append(profile.getConnectionAuthorization().getName()).append("',");
            result.append("ConnectionAuthorization value: '").append(profile.getConnectionAuthorization().getValue()).append("',");
        }
        result.append("Headline value: '").append(profile.getHeadline()).append("', ");
        result.append("Industry value: '").append(profile.getIndustry()).append("', ");
        result.append("ExtraData value: '").append(profile.getExtraData()).append("'");
        result.append("]");
        return result.toString();
    }
}
