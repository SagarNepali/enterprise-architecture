package edu.mum.cs544.config;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MyWebApplicationInitializer implements org.springframework.web.WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
        springContext.register(SpringConfig.class);

        servletContext.addListener(new ContextLoaderListener(springContext));

    }
}
