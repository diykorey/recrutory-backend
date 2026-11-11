package com.kandidato.persistence.entity;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "USERS")
public class User implements Entity {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "LOGIN")
    private String login = "";

    @Column(name = "PASSWORD")
    private byte[] password;

    @Column(name = "FIRST_NAME")
    private String firstName = "";

    @Column(name = "LAST_NAME")
    private String lastName = "";


    @Column(name = "FACEBOOK_ID")
    private String facebookId;

    @Column(name = "LINKEDIN_ID")
    private String linkedinId;

    @Column(name = "GOOGLE_ID")
    private String googlePlusId;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getLinkedinId() {
        return linkedinId;
    }

    public void setLinkedinId(String linkedinId) {
        this.linkedinId = linkedinId;
    }

    public String getGooglePlusId() {
        return googlePlusId;
    }

    public void setGooglePlusId(String googlePlusId) {
        this.googlePlusId = googlePlusId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login == null)
            login = "";
        this.login = login;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        if (firstName == null)
            firstName = "";
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null)
            lastName = "";
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", facebookId='").append(facebookId).append('\'');
        sb.append(", linkedinId='").append(linkedinId).append('\'');
        sb.append(", googlePlusId='").append(googlePlusId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static User createFbUser(String profileId, String login, String firstName, String lastName) {
        User user = createUser(login, firstName, lastName);
        user.facebookId = profileId;
        return user;
    }

    public static User createGoogleUser(String profileId, String login, String firstName, String lastName) {
        User user = createUser(login, firstName, lastName);
        user.googlePlusId = profileId;
        return user;
    }

    public static User createLinkedinUser(String profileId, String login, String firstName, String lastName) {
        User user = createUser(login, firstName, lastName);
        user.linkedinId = profileId;
        return user;
    }

    public static User createUser(String login, String firstName, String lastName) {
        User user = new User();
        user.login = login;
        user.firstName = firstName;
        user.lastName = lastName;
        return user;
    }

}
