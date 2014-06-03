package com.kandidato.config;

import com.kandidato.security.DummyAuthenticationFilter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import java.util.EnumSet;

/**
 * Created by andriy on 5/6/14.
 */
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        //TODO Remove this filter for non-development environments.
        FilterRegistration.Dynamic filter = servletContext.addFilter("Dummy_authentication_filter", new DummyAuthenticationFilter());
        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
        super.beforeSpringSecurityFilterChain(servletContext);
    }
}
