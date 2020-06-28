package com.qc.ms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * quincey
 * Date 2020/6/28 19:18
 */

@SpringBootApplication
@EnableZuulProxy  // 开启路由代理功能
public class MsZuul9001 {
    public static void main(String[] args) {
        SpringApplication.run(MsZuul9001.class, args);
    }
}
