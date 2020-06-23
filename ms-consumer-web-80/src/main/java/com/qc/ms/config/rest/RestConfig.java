package com.qc.ms.config.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

/**
 * quincey
 * Date 2020/6/23 15:57
 */

@Configuration
public class RestConfig {

    @Bean
    RestTemplate restTemplate() {// 这里如果路径之中由 findUserByQuery?参数名=参数值&参数名2=h5%2abc
        // 还需要配置！！不需要 转 参数路径字符串
        RestTemplate restTemplate = new RestTemplate();
        DefaultUriBuilderFactory uriFactory = new DefaultUriBuilderFactory();
        // 路径转码加密方式为不设置
        uriFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);// 对地址不进行编码，在请求之前自己做好编码
        restTemplate.setUriTemplateHandler(uriFactory);
        return restTemplate;
    }
}
