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
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource({"classpath:com/kandidato/config/persistence.properties"})
public class PersistenceConfig {

    private static final Logger log = LoggerFactory.getLogger(PersistenceConfig.class);

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        log.warn("Get new sesssion factory");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.kandidato.persistence"});
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        log.warn("Get new data source");
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(100);
        //config.setDataSourceClassName(env.getProperty("jdbc.dataSourceName"));
        config.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        config.setJdbcUrl(env.getProperty("jdbc.url"));
        config.setUsername(env.getProperty("jdbc.user"));
        config.setPassword(env.getProperty("jdbc.pass"));
       // config.setAutoCommit(false);
        config.addDataSourceProperty("cachePrepStmts", env.getProperty("jdbc.cachePrepStmts") );
        config.addDataSourceProperty("prepStmtCacheSize", env.getProperty("jdbc.prepStmtCacheSize") );
        config.addDataSourceProperty("prepStmtCacheSqlLimit", env.getProperty("jdbc.prepStmtCacheSqlLimit") );
        config.addDataSourceProperty("useServerPrepStmts", env.getProperty("jdbc.useServerPrepStmts") );

        return new HikariDataSource(config);
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
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
