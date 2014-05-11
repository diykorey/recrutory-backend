package com.kandidato.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan(basePackages = "com.kandidato", excludeFilters = {@Filter(Configuration.class)})
@Import({PersistenceConfig.class, SecurityConfig.class })
public class MainConfig {


}
