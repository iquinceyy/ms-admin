package com.qc.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableDiscoveryClient// 满足那种比较老的技术还在用zk实现 注册中心。
@EnableFeignClients(basePackages = "com.qc.ms.service")
public class MsConsumerWeb80Application {

    public static void main(String[] args) {
        SpringApplication.run(MsConsumerWeb80Application.class, args);
    }

}
