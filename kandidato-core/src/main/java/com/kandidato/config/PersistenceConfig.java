package com.kandidato.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource({"classpath:com/kandidato/config/persistence.properties"})
@EnableJpaRepositories(basePackages = {"com.kandidato.persistence.repository"})
@EnableTransactionManagement
public class PersistenceConfig {

    private static final Logger log = LoggerFactory.getLogger(PersistenceConfig.class);

    @Autowired
    private Environment env;

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(new String[]{"com.kandidato.persistence.entity", "com.kandidato.persistence.entity.comment"});
        factory.setDataSource(dataSource());
        factory.setJpaProperties(hibernateProperties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(newHikariConfig("jdbc.url"));
    }

    @Bean
    public DataSource quartzDataSource() {
        return new HikariDataSource(newHikariConfig("quartz.jdbc.url"));
    }

    private HikariConfig newHikariConfig(String jdbcUrlPropertyName) {
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(50);

        config.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        config.setJdbcUrl(env.getProperty(jdbcUrlPropertyName));
        config.setUsername(env.getProperty("jdbc.user"));
        config.setPassword(env.getProperty("jdbc.pass"));

        config.addDataSourceProperty("cachePrepStmts", env.getProperty("jdbc.cachePrepStmts"));
        config.addDataSourceProperty("prepStmtCacheSize", env.getProperty("jdbc.prepStmtCacheSize"));
        config.addDataSourceProperty("prepStmtCacheSqlLimit", env.getProperty("jdbc.prepStmtCacheSqlLimit"));
        config.addDataSourceProperty("useServerPrepStmts", env.getProperty("jdbc.useServerPrepStmts"));

        return config;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
        return jpaTransactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
                setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
                setProperty("hibernate.globally_quoted_identifiers", "true");
            }
        };
    }
}
