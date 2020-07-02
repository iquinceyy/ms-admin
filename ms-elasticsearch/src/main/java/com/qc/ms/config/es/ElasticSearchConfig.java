package com.qc.ms.config.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

/**
 * quincey
 * Date 2020/7/1 20:03
 */

@SpringBootConfiguration
public class ElasticSearchConfig {

    private final static Logger log = LoggerFactory.getLogger(ElasticSearchConfig.class);
    private static final String SCHEMA = "http";
    // 使用的协议
    private ArrayList<HttpHost> hostList;
    // @Value("${spring.data.elasticsearch.host}")
    private String hosts = "120.25.223.121";
    // 集群地址，多个用,隔开
    // @Value("${spring.data.elasticsearch.port}")
    private int port = 9200;
    // 集群地址，多个用,隔开
    // @Value("${spring.data.elasticsearch.connectTimeOut}")
    private int connectTimeOut = 1000; // 连接超时时间
    //@Value("${spring.data.elasticsearch.socketTimeOut}")
    private int socketTimeOut = 30000; // 连接超时时间
    // @Value("${spring.data.elasticsearch.connectionRequestTimeOut}")
    private int connectionRequestTimeOut = 500; // 获取连接的超时时间
    // @Value("${spring.data.elasticsearch.maxConnectNum}")
    private int maxConnectNum = 100;
    // 最大连接数////
    // @Value("${spring.data.elasticsearch.maxConnectPerRoute}")
    private int maxConnectPerRoute = 100;
    // 最大路由连接数
    private RestClientBuilder builder;

    @Bean
    @ConditionalOnMissingBean(RestHighLevelClient.class)
    public RestHighLevelClient client() {
        hostList = new ArrayList<>();
        String[] hostStrs = hosts.split(",");
        for (String host : hostStrs) {
            String server = host;
            hostList.add(new HttpHost(host, port, SCHEMA));
        }
        builder = RestClient.builder(hostList.toArray(new HttpHost[0]));
        setConnectTimeOutConfig();
        setMutiConnectConfig();
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }

    // 异步httpclient的连接延时配置
    public void setConnectTimeOutConfig() {
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(connectTimeOut);
            requestConfigBuilder.setSocketTimeout(socketTimeOut);
            requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
            return requestConfigBuilder;
        });
    }

    // 异步httpclient的连接数配置
    public void setMutiConnectConfig() {
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(maxConnectNum);
            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
            return httpClientBuilder;
        });
    }

}
