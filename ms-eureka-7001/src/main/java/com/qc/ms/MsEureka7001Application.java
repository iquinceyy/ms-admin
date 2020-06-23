package com.qc.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer// 开启eureka服务注册中心

public class MsEureka7001Application {

    public static void main(String[] args) {
        SpringApplication.run(MsEureka7001Application.class, args);
    }

}
