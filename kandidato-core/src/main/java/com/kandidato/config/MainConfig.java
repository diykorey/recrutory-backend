package com.kandidato.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = "com.kandidato", excludeFilters = {@Filter(Configuration.class)})
public class MainConfig {

}
