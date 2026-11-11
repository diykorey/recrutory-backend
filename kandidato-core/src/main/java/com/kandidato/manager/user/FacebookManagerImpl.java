package com.kandidato.manager.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookServiceProvider;
import org.springframework.stereotype.Component;

@Component
public class FacebookManagerImpl implements FacebookManager{

    @Autowired
    private FacebookServiceProvider facebookServiceProvider;

    @Override
    public User getProfile(String token) {
        Facebook api = facebookServiceProvider.getApi(token);
        return api.userOperations().getUserProfile();
    }
}
