package com.qc.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * quincey
 * Date 2020/7/2 19:36
 */

@SpringBootApplication
@EnableTransactionManagement
public class MsLCNServer {
    public static void main(String[] args) {
        SpringApplication.run(MsLCNServer.class, args);
    }
}
