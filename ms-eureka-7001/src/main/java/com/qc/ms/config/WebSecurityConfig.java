package com.qc.ms.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * quincey
 * Date 2020/6/28 20:25
 */

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 忽略掉/eureka/**
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}
