package com.kandidato.manager.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.connect.LinkedInServiceProvider;
import org.springframework.stereotype.Component;

@Component
public class LinkedInManagerImpl implements LinkedInManager{

    @Autowired
    private LinkedInServiceProvider linkedInProvider;

    @Override
    public LinkedInProfile getProfile(String token) {
        LinkedIn api = linkedInProvider.getApi(token);
        return api.profileOperations().getUserProfile();
    }
}
