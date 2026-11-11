package com.kandidato.config;

import com.kandidato.persistence.search.repository.ResumeSearchRepository;
import com.kandidato.persistence.search.repository.VacancySearchRepository;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchRepositoryFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.kandidato.persistence.search.repository")
@PropertySource(value = "classpath:com/kandidato/config/elastic-${env}.properties")
public class ElasticSearchConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchConfig.class);

    @Autowired
    private Environment env;

    @Bean(name = "elasticsearchTemplate")
    public ElasticsearchOperations createElasticSearchTemplate() throws UnknownHostException {
        String host = env.getProperty("elastic.host");
        int port = env.getProperty("elastic.port", Integer.class);

        LOG.info("Creating elasticsearch template with the following host : {} and port : {}", host, port);

        TransportAddress address = new InetSocketTransportAddress(InetAddress.getByName(host), port);
        Client client = TransportClient.builder().build().addTransportAddress(address);

        return new ElasticsearchTemplate(client);
    }

    @Bean(name = "elasticSearchRepositoryFactory")
    @Autowired
    public ElasticsearchRepositoryFactory createElasticSearchRepositoryFactory(ElasticsearchOperations elasticsearchTemplate) {
        return new ElasticsearchRepositoryFactory(elasticsearchTemplate);
    }

    @Bean(name = "resumeSearchRepository")
    @Autowired
    public ResumeSearchRepository createResumeSearchRepository(ElasticsearchRepositoryFactory factory) {
        return factory.getRepository(ResumeSearchRepository.class);
    }

    @Bean(name = "vacancySearchRepository")
    @Autowired
    public VacancySearchRepository createVacancySearchRepository(ElasticsearchRepositoryFactory factory) {
        return factory.getRepository(VacancySearchRepository.class);
    }
}