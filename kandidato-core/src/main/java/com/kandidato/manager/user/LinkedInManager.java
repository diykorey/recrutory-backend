package com.kandidato.manager.user;


import org.springframework.social.linkedin.api.LinkedInProfile;

public interface LinkedInManager {
    LinkedInProfile getProfile(String token);
}
