package com.kandidato.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.social.facebook.connect.FacebookServiceProvider;
import org.springframework.social.google.connect.GoogleServiceProvider;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.connect.LinkedInServiceProvider;

@Configuration
@PropertySource("classpath:com/kandidato/config/social-common.properties")
public class SocialConfig {

    @Autowired
    private Environment env;

    @Bean
    public FacebookServiceProvider facebook() {
        return new FacebookServiceProvider("", "", "");
    }

    @Bean
    public LinkedInServiceProvider linkedin() {
        String clientId = env.getProperty("linkedin.clientId");
        String clientSecret = env.getProperty("linkedin.clientSecret");
        return new LinkedInServiceProvider(clientId, clientSecret);
    }

    @Bean
    public GoogleServiceProvider google() {
        return new GoogleServiceProvider("", "");
    }

}
