package com.salpreh.qdo.configurations;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.EnableElasticsearchAuditing;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.salpreh.qdo.repositories")
@EnableElasticsearchAuditing
public class ElasticsearchConfig {
	
	@Value("${spring.data.elasticsearch.url}")
	private String elasticsearchUrl;

	@Bean
	public RestHighLevelClient elasticsearchClient() {
		ClientConfiguration clientConfig = ClientConfiguration.builder()
			.connectedTo(elasticsearchUrl)
			.build();
		
		return RestClients.create(clientConfig).rest();
	}
	
//	@Bean
//	public ElasticsearchOperations elasticsearchTemplate() {
//		return new ElasticsearchRestTemplate(this.elasticsearchClient());
//	}

}
