package com.qc.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient// 满足那种比较老的技术还在用zk实现 注册中心。
public class MsConsumerWeb80Application {

    public static void main(String[] args) {
        SpringApplication.run(MsConsumerWeb80Application.class, args);
    }

}
