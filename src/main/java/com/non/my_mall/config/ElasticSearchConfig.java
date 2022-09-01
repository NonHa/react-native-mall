package com.non.my_mall.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Configuration
public class ElasticSearchConfig {
    @Value("${spring.elasticsearch.uris}")
    private String elasticsearchUris;

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        //设置账号密码
//        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "QEnmjvqe1*NBBx+7sOgX"));


        RestClient client = RestClient.builder(new HttpHost("127.0.0.1", 9200,"http"))
                .setHttpClientConfigCallback(httpClientBuilder->httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)).build();;
        ElasticsearchTransport transport = new RestClientTransport(client,new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }



}
