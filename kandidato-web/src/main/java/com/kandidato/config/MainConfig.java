package com.kandidato.config;


import com.kandidato.constants.VacancyState;
import com.kandidato.util.VacancyStateEnumConverter;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;


@Configuration
@ComponentScan(basePackages = "com.kandidato", excludeFilters = {@Filter(Configuration.class)})
public class MainConfig {


}
