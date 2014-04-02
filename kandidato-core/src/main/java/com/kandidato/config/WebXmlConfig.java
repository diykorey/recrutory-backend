package com.kandidato.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created by diyko on 02.04.2014.
 */
public class WebXmlConfig implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext container) throws ServletException {
        ServletRegistration.Dynamic dispatcher = container.addServlet("SpringDispatcher", DispatcherServlet.class);
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1);
        dispatcher.setInitParameter("contextClass","org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
        dispatcher.setInitParameter("contextConfigLocation","com.kandidato.config");
    }
}

