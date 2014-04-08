package com.kandidato.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchRepositoryFactory;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.kandidato.persistence.search")
@PropertySource({"classpath:com/kandidato/config/elasticsearch.properties"})
public class ElasticSearchConfig {

    @Autowired
    private Environment env;

    @Bean(name = "elasticsearchTemplate")
    public ElasticsearchOperations createElasticSearchTemplate() {
        String host = env.getProperty("host");
        int port = env.getProperty("port", Integer.class);

        Client client = new TransportClient().addTransportAddress(new InetSocketTransportAddress(host, port));
        return new ElasticsearchTemplate(client);
    }

    @Bean(name = "elasticSearchRepositoryFactory")
    @Autowired
    public ElasticsearchRepositoryFactory createElasticSearchRepositoryFactory(ElasticsearchOperations elasticsearchTemplate) {
        return new ElasticsearchRepositoryFactory(elasticsearchTemplate);
    }
}
