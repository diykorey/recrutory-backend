package com.kandidato.manager.user;

import org.springframework.social.google.api.plus.Person;

public interface GoogleManager {
    Person getProfile(String token);
}
