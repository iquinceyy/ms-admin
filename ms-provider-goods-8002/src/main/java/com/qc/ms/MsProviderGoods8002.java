package com.qc.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * quincey
 * Date 2020/7/1 20:07
 */

@SpringBootConfiguration
@EnableDiscoveryClient
public class MsProviderGoods8002 {
    public static void main(String[] args) {
        SpringApplication.run(MsProviderGoods8002.class,args);
    }
}
