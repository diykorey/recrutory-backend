package com.kandidato.manager.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.connect.GoogleServiceProvider;
import org.springframework.stereotype.Component;

@Component
public class GoogleManagerImpl implements GoogleManager{

    @Autowired
    private GoogleServiceProvider googleServiceProvider;

    @Override
    public Person getProfile(String token) {
        Google api = googleServiceProvider.getApi(token);
        return api.plusOperations().getGoogleProfile();
    }
}
