package com.kandidato.manager.user;

import com.kandidato.model.dto.TokenDTO;
import com.kandidato.model.dto.UserDTO;
import com.kandidato.security.model.CurrentProfile;

import java.util.Optional;

public interface UserManager {
    String FACEBOOK = "facebook";
    String GOOGLE = "google";
    String LINKEDIN = "linkedIn";

    UserDTO attachNewProvider(CurrentProfile currentUser, TokenDTO token);

    Optional<CurrentProfile> getCurrentProfile(String provider, String token);
}
