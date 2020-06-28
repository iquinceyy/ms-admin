package com.qc.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer // 开启 注解表示这是一个config的服务器
public class MsConfigServer10001 {
    public static void main(String[] args) {
        SpringApplication.run(MsConfigServer10001.class, args);
    }
}
