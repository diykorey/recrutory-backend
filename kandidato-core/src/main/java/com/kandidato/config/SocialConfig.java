package com.kandidato.config;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;

@Configuration
@EnableSocial
@PropertySource("classpath:com/kandidato/config/social.properties")
public class SocialConfig implements SocialConfigurer {

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public LinkedIn linkedIn(ConnectionRepository repository) {
        Connection<LinkedIn> connection = repository.findPrimaryConnection(LinkedIn.class);
        return connection != null ? connection.getApi() : null;
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment env) {
        configurer.addConnectionFactory(new LinkedInConnectionFactory(
                env.getProperty("linkedin.apiKey"),
                env.getProperty("linkedin.secretKey")
        ));
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new UserIdSource(){
            @Override
            public String getUserId() {
                return "Mykola";
            }
        };
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator locator) {
        return new InMemoryUsersConnectionRepository(locator);
    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }
}
