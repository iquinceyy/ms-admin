package com.qc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker  //开启融到机制
public class MsProviderUser8001 {
    public static void main(String[] args) {
        SpringApplication.run(MsProviderUser8001.class, args);
    }
}
