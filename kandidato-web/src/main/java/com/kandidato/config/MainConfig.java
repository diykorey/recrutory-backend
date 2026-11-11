package com.kandidato.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@ComponentScan(basePackages = "com.kandidato", excludeFilters = {@Filter(Configuration.class)})
@Import({PersistenceConfig.class, MapperConfig.class, SocialConfig.class, ElasticSearchConfig.class})
@PropertySource(value = "classpath:com/kandidato/config/common.properties")
@EnableScheduling
public class MainConfig {
}
