package com.kandidato.manager.user;


import org.springframework.social.facebook.api.User;

public interface FacebookManager {
    User getProfile(String token);
}
