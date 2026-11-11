package com.kandidato.config;

import com.kandidato.security.auth.CurrentUserResolver;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.controllers.DefaultSwaggerController;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.paths.SwaggerPathProvider;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletContext;
import java.util.List;

@Configuration
@EnableWebMvc
@EnableSwagger
@PropertySource(value = "classpath:com/kandidato/config/general-${env}.properties")
@ComponentScan(basePackages = "com.kandidato", excludeFilters = {@ComponentScan.Filter(Configuration.class)})
@EnableTransactionManagement
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    public static final int MAX_UPLOAD_SIZE_BYTES = 5000000;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    private Environment env;

    @Bean //Don't forget the @Bean annotation
    public SwaggerSpringMvcPlugin customImplementation() {
        RecrutoryAbsoluteSwaggerPathProvider pathProvider = new RecrutoryAbsoluteSwaggerPathProvider(servletContext);

        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).pathProvider(pathProvider)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Recrutory API",
                "Recruitment workflow manager",
                "",/*My Apps API terms of service*/
                "info@recrutory.com",
                "",/*My Apps API Licence Type*/
                ""/*My Apps API License URL*/
        );
        return apiInfo;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new CurrentUserResolver());
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        return messageSource;
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * Supports FileUploads.
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(MAX_UPLOAD_SIZE_BYTES);
        return multipartResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/**");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/**");
        registry.addResourceHandler("kandidato.html").addResourceLocations("kandidato.html");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(messageSource());
        return validatorFactoryBean;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }


    private  class RecrutoryAbsoluteSwaggerPathProvider extends SwaggerPathProvider {


        private ServletContext servletContext;

        public RecrutoryAbsoluteSwaggerPathProvider(ServletContext servletContext) {
            this.servletContext = servletContext;
            setApiResourcePrefix("recrutory");
        }

        @Override
        protected String applicationPath() {
            return getAppRoot()
                    .build()
                    .toString();
        }

        @Override
        protected String getDocumentationPath() {
            return getAppRoot()
                    .path(servletContext.getContextPath())
                    .path(DefaultSwaggerController.DOCUMENTATION_BASE_PATH)
                    .build()
                    .toString();
        }

        private UriComponentsBuilder getAppRoot() {
            return UriComponentsBuilder.fromHttpUrl(env.getProperty("root"));
        }
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
