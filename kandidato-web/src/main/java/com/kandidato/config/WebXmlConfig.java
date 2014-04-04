package com.kandidato.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by diyko on 02.04.2014.
 */
public class WebXmlConfig implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext container) throws ServletException {

        ServletRegistration.Dynamic dispatcher = container.addServlet("SpringDispatcher", DispatcherServlet.class);
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1);
        dispatcher.setInitParameter("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
        dispatcher.setInitParameter("contextConfigLocation", "com.kandidato.config");

        //FIXME Configure opensessioninview
//        FilterRegistration.Dynamic sessionInViewFilter = container.addFilter("openSessionInViewFilter", "org.springframework.orm.hibernate4.support.OpenSessionInViewFilter");
//        sessionInViewFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

    }


}

