package com.kandidato.config;

import org.apache.catalina.filters.CorsFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by diyko on 02.04.2014.
 */
public class WebXmlConfig implements WebApplicationInitializer {

    private static final Logger log = LoggerFactory.getLogger(WebXmlConfig.class);


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext rootContext = createRootContext(servletContext);
        configureFilters(servletContext);
        configureSpringMvc(servletContext, rootContext);
    }

    private WebApplicationContext createRootContext(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(MainConfig.class);
        rootContext.refresh();
        servletContext.addListener(new ContextLoaderListener(rootContext));
        servletContext.setInitParameter("defaultHtmlEscape", "true");

        return rootContext;
    }

    private void configureSpringMvc(ServletContext servletContext, WebApplicationContext rootContext) {
        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        mvcContext.setServletContext(servletContext);
        mvcContext.register(WebXmlConfig.class, WebMvcConfig.class);
        mvcContext.setParent(rootContext);
        mvcContext.refresh();

        ServletRegistration.Dynamic appServlet = servletContext.addServlet(
                "SpringDispatcher", new DispatcherServlet(mvcContext));

        appServlet.setLoadOnStartup(1);
        Set<String> mappingConflicts = appServlet.addMapping("/");

        if (!mappingConflicts.isEmpty()) {
            for (String s : mappingConflicts) {
                log.error("Mapping conflict: {}", s);
            }
            throw new IllegalStateException(
                    "'webservice' cannot be mapped to '/'");
        }
    }

    private void configureFilters(ServletContext ctx) {
        FilterRegistration.Dynamic filter = ctx.addFilter("openEntityManagerInView", OpenEntityManagerInViewFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");

        FilterRegistration.Dynamic authFilter = ctx.addFilter("authenticationFilter", DelegatingFilterProxy.class);
        authFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");

        /*FilterRegistration.Dynamic cors = ctx.addFilter("corsFilter", CorsFilter.class);
        cors.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
        cors.setInitParameter("cors.allowed.origins", "*");
        cors.setInitParameter("cors.allowed.methods", "GET,POST,PUT,DELETE");
        cors.setInitParameter("cors.allowed.headers", "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers");
        cors.setInitParameter("cors.exposed.headers", "Access-Control-Allow-Origin,Access-Control-Allow-Credentials");
        cors.setInitParameter("cors.support.credentials", "true");
        cors.setInitParameter("cors.preflight.maxage", "10");*/
    }
}

