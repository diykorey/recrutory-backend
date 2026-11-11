package com.kandidato.security.model;


import com.kandidato.manager.user.UserManager;
import com.kandidato.persistence.entity.User;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.linkedin.api.LinkedInProfile;

public class CurrentProfile {
    private final User user;
    private final String provider;
    private final String email;

    public CurrentProfile(User user, String provider, String email, String firstName, String lastName) {
        this.user = user;
        this.provider = provider;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private final String firstName;
    private final String lastName;

    public CurrentProfile(User user, Person person) {
        this.user = user;
        this.provider = UserManager.GOOGLE;
        this.email = person.getAccountEmail();
        this.firstName = person.getGivenName();
        this.lastName = person.getFamilyName();
    }

    public CurrentProfile(User user, LinkedInProfile profile) {
        this.user = user;
        this.provider = UserManager.LINKEDIN;
        this.email = profile.getEmailAddress();
        this.firstName = profile.getFirstName();
        this.lastName = profile.getLastName();
    }

    public CurrentProfile(User user,
                          org.springframework.social.facebook.api.User facebookUser) {
        this.user = user;
        this.provider = UserManager.FACEBOOK;
        this.email = facebookUser.getEmail();
        this.firstName = facebookUser.getFirstName();
        this.lastName = facebookUser.getLastName();
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public String getProvider() {
        return provider;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CurrentProfile{");
        sb.append("user=").append(user);
        sb.append(", provider='").append(provider).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
