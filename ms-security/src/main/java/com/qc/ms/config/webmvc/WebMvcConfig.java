package com.qc.ms.config.webmvc;

import com.qc.ms.config.interceptor.PrincipaIValidate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * quincey
 * Date 2020/6/24 16:22
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    PrincipaIValidate principaIValidate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(principaIValidate);
    }
}
